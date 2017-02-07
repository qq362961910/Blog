package com.jy.helper;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenHelper {

    private static final Logger logger = LogManager.getLogger(TokenHelper.class);

    private final Object blockThreadCountLock = new Object();
    private volatile int blockThreadCount = 0;

    /**
     * 默认110分钟更新一次token
     * */
    private static final int DEFAULT_TOKEN_EXPIRE_TIIME_IN_MILLS = 1000 * 60 * 110;
    /**
     * 默认1分钟检查一次Token是否有效
     * */
    private static final int DEFAULT_CHECK_TOKEN_EXPIRE_TIME_IN_MILLS = 500;
    /**
     * 默认token服务生命时长
     * */
    private static final int DEFAULT_TOKEN_SERVICE_LIFE_TIMEOUT_IN_MILLS = 1000 * 10;
    /**
     * token存活时间
     * */
    private final int tokenExpireTime;
    /**
     * 使用token服务超时期限
     * */
    private final int serviceLifeTimeout;
    /**
     * 默认token加载器
     * */
    private final TokenLoader defaultTokenLoader;
    /**
     * token缓存容器
     * */
    private volatile Map<String, String> tokenMapping = new HashMap<>();
    /**
     * token最近更新一次时间映射
     * */
    private volatile Map<String, Long> tokenLastUpdateTimeMapping = new HashMap();
    /**
     * 正在加载token的eky
     * */
    private volatile List<String> loadingTokenKeys = new ArrayList<>();
    /**
     * 正在使用中的token
     * */
    private volatile Map<String, Long> tokenInUseMapping = new ConcurrentHashMap<>();
    /**
     * 正在使用的token数量
     * */
    private volatile Map<String, Integer> tokenInUseCount = new HashMap();
    /**
     * token加载器映射
     * */
    private volatile Map<String, TokenLoader> tokenLoaderMapping = new HashMap();
    /**
     * 状态
     * */
    private volatile boolean closed = false;

    public TokenHelper() {
        this(null);
    }

    public TokenHelper(TokenLoader tokenLoader) {
        this(DEFAULT_TOKEN_EXPIRE_TIIME_IN_MILLS, DEFAULT_TOKEN_SERVICE_LIFE_TIMEOUT_IN_MILLS, tokenLoader);
    }

    public TokenHelper(int tokenExpireTime, int serviceLifeTimeout, TokenLoader defaultTokenLoader) {
        this.tokenExpireTime = tokenExpireTime;
        this.serviceLifeTimeout = serviceLifeTimeout;
        this.defaultTokenLoader = defaultTokenLoader;
        new Thread(){
            @Override
            public void run() {
                while (!closed) {
                    try {
                        long currentTimeMillis = System.currentTimeMillis();
                        //遍历过期Token
                        for (Map.Entry<String, Long> entry: tokenInUseMapping.entrySet() ) {
                            //判断是否使用超时
                            if (currentTimeMillis - TokenHelper.this.serviceLifeTimeout > entry.getValue()) {
                                logger.info("usage time exceed limit, remove key from tokenInUseMapping: " + entry.getKey());
                                tokenInUseMapping.remove(entry.getKey());
                                logger.info("###########################################################################");
                                logger.info("service life expired, key: " + entry.getKey());
                                logger.info("###########################################################################");
                            }
                        }
                        Thread.sleep(DEFAULT_CHECK_TOKEN_EXPIRE_TIME_IN_MILLS);
                    } catch (Exception e) {
                        StackTraceElement[] stackTraceElements = e.getStackTrace();
                        for (StackTraceElement stackTraceElement: stackTraceElements) {
                            logger.error(stackTraceElement.toString());
                        }
                    }
                }
            }
        }.start();
    }

    public String getToken(String key) {
        long currentTimeMillis = System.currentTimeMillis();
        long lastUpdateTime = tokenLastUpdateTimeMapping.get(key) == null ? 0 : tokenLastUpdateTimeMapping.get(key);
        //判断缓存token是否过期
        if (lastUpdateTime <= currentTimeMillis - tokenExpireTime) {
            try {
                logger.info("find a expired key: " + key);
                loadToken(key);
            } catch (Exception e) {
                StackTraceElement[] stackTraceElements = e.getStackTrace();
                for (StackTraceElement stackTraceElement: stackTraceElements) {
                    logger.error(stackTraceElement.toString());
                }
            }
        }
        //更新最后使用时间
        tokenInUseMapping.put(key, System.currentTimeMillis());
        //计数token使用量
        increaseTokenInUse(key, 1);
        return tokenMapping.get(key);
    }

    public void releaseToken(String key) {
        synchronized (key.intern()) {
            int count = decreaseTokenInUse(key, 1);
            logger.info("release key: " +key + ", in using: " + count);
            if (count == 0) {
                tokenInUseMapping.remove(key);
                logger.info("key: " + key + " is not in using, remove it from KEY IN USING COUNTER");
            }
        }
    }

    public void loadToken(String key) throws InterruptedException {
        //register key
        boolean isLoadingTokenThread = registerLoadingKey(key);
        if (isLoadingTokenThread) {
            try {
                TokenLoader tokenLoader = tokenLoaderMapping.get(key);
                if (tokenLoader == null) {
                    tokenLoader = defaultTokenLoader;
                }
                if (tokenLoader == null) {
                    throw new RuntimeException("No TokenLoader Instance found");
                }
                while (tokenInUseMapping.containsKey(key)) {
                    Thread.sleep(500);
                    logger.info("continue waiting, key: " + key);
                }
                String token = tokenLoader.loadToken(key);
                tokenMapping.put(key, token);
                tokenLastUpdateTimeMapping.put(key, System.currentTimeMillis());
                if (tokenInUseCount.get(key) == null) {
                    tokenInUseCount.put(key, 0);
                }
                logger.info("key: " + key +", load token successfully, token: " + token);

            } catch (Exception e){
                logger.error("load token error for key:" + key);
                StackTraceElement[] stackTraceElements = e.getStackTrace();
                for (StackTraceElement stackTraceElement: stackTraceElements) {
                    logger.error(stackTraceElement.toString());
                }
            }
            finally {
                synchronized (key.intern()) {
                    //Notify
                    loadingTokenKeys.remove(key);
                    key.intern().notifyAll();
                }
            }
        }
    }

    /**
     * 注册正在加载中的key
     * */
    private boolean registerLoadingKey(String key) throws InterruptedException {
        synchronized (key.intern()) {
            if (loadingTokenKeys.contains(key)) {
                logger.info("key: " + key + " is loading by other threads, wating......, thread-name: " + Thread.currentThread().getName());
                synchronized (blockThreadCountLock) {
                    blockThreadCount++;
                }
                key.intern().wait(5000);
                logger.info("thread-name: " + Thread.currentThread().getName() + " is waked by other thread");
                synchronized (blockThreadCountLock) {
                    blockThreadCount--;
                }
                return false;
            }
            else {
                loadingTokenKeys.add(key);
                logger.info("register key successfully: " + key);
                return true;
            }
        }
    }

    /**
     * 添加token加载器
     * */
    public void addTokenLoader(String key, TokenLoader tokenLoader) {
        tokenLoaderMapping.put(key, tokenLoader);
    }

    /**
     * 查看对应key的TokenLoader是否存在
     * */
    public boolean tokenLoaderExist(String key) {
        return tokenLoaderMapping.containsKey(key);
    }

    /**
     * safe
     * 增加token使用计数
     * */
    private int increaseTokenInUse(String key, int count) {
        synchronized (key.intern()) {
            int newCount = tokenInUseCount.get(key) + count;
            tokenInUseCount.put(key, newCount);
            return newCount;
        }
    }

    /**
     * Unsafe
     * 减少token使用计数
     * */
    private int decreaseTokenInUse(String key, int count) {
        int newCount = tokenInUseCount.get(key) - count;
        tokenInUseCount.put(key, newCount);
        return newCount;
    }

    /**
     * Token加载器
     * */
    public interface TokenLoader {
        String loadToken(String key);
    }

    /**
     * 阻塞线程数统计
     * */
    public int getBlockThreadCount() {
        return blockThreadCount;
    }

    public void close () {
        closed = true;
    }


}
