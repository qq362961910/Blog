package com.jy.blog.helper;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.JedisCluster;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class TokenHelper {

    private static final Logger logger = LogManager.getLogger(TokenHelper.class);

    /**
     * 默认token有效时长110分钟
     */
    private static final int DEFAULT_TOKEN_EXPIRE_TIIME_IN_SECOND = 60 * 110;

    /**
     * 默认加载TOken超时时间
     */
    private static final int DEFAULT_LOAD_TOKEN_TIMEOUT_IN_MILLS = 1000 * 5;

    /**
     * 默认key锁超时时间
     */
    private static final int DEFAULT_KEY_LOCK_TIMEOUT_IN_MILLS = 1000 * 5;

    /**
     * token存活时间
     */
    private final int tokenExpireTime;

    /**
     * 默认TOken加载器需要redis支持
     */
    private JedisCluster jedisCluster;

    /**
     * 默认token加载器
     */
    private TokenLoader defaultTokenLoader = null;


    /**
     * token加载器映射
     */
    private Map<String, TokenLoader> tokenLoaderMapping = new HashMap();


    public TokenHelper(JedisCluster jedisCluster) {
        this(jedisCluster, DEFAULT_TOKEN_EXPIRE_TIIME_IN_SECOND);
    }

    public TokenHelper(JedisCluster jedisCluster, int tokenExpireTime) {
        this(jedisCluster, tokenExpireTime, null);
    }

    public TokenHelper(JedisCluster jedisCluster, TokenLoader defaultTokenLoader) {
        this(jedisCluster, DEFAULT_TOKEN_EXPIRE_TIIME_IN_SECOND, defaultTokenLoader);
    }

    public TokenHelper(JedisCluster jedisCluster, int tokenExpireTime, TokenLoader defaultTokenLoader) {
        this.jedisCluster = jedisCluster;
        this.tokenExpireTime = tokenExpireTime;
        this.defaultTokenLoader = defaultTokenLoader;
    }

    public String getToken(String key, boolean nullable) throws TimeoutException {
        String token = loadToken(key);
        if (token == null && !nullable) {
            throw new TimeoutException("get token timeout for key: " + key);
        }
        logger.info("return token, key: " + key + ", current token: " + token);
        return token;
    }

    public String loadToken(String key) {
        TokenLoader tokenLoader = tokenLoaderMapping.get(key);
        if (tokenLoader == null) {
            tokenLoader = defaultTokenLoader;
        }
        if (tokenLoader == null) {
            throw new RuntimeException("No TokenLoader Instance found");
        }
        return tokenLoader.loadToken(key, tokenLoader.loadTokenTimeoutInMills());
    }

    /**
     * 添加token加载器
     */
    public synchronized void addTokenLoader(String key, TokenLoader tokenLoader) {
        tokenLoaderMapping.put(key, tokenLoader);
    }

    /**
     * 查看对应key的TokenLoader是否存在
     */
    public boolean tokenLoaderExist(String key) {
        return tokenLoaderMapping.containsKey(key);
    }

    /**
     * Token加载器
     */
    public interface TokenLoader {
        String loadToken(String key, long timeout);

        long loadTokenTimeoutInMills();
    }

    public TokenHelper setDefaultTokenLoader(TokenLoader defaultTokenLoader) {
        this.defaultTokenLoader = defaultTokenLoader;
        return this;
    }

    /**
     * Token加载器默认实现
     */
    public static abstract class AbstractTokenLoader implements TokenLoader {

        private static final Logger logger = LogManager.getLogger(AbstractTokenLoader.class);

        private static final ThreadLocal<String> threadLocalKeyLockValue = new ThreadLocal<String>();

        private static final String KEY_LOCK_TAIL = "_lock";

        private TokenHelper tokenHelper;

        @Override
        public String loadToken(String key, long timeout) {

            String token = tokenHelper.jedisCluster.get(key);
            //token失效
            while (timeout > 0 && token == null) {
                logger.info("empty token key: " + key);
                String keyLock = key + KEY_LOCK_TAIL;
                boolean acquireLockSuccess = acquireLock(keyLock);
                if (acquireLockSuccess) {
                    logger.info("acquire lock success for key: " + key);
                    token = loadTokenImpl(key);
                    String oldToken = tokenHelper.jedisCluster.getSet(key, token);
                    boolean releaseLockSuccess = releaseLock(keyLock);
                    if (releaseLockSuccess) {
                        //设置超时
                        tokenHelper.jedisCluster.expire(key, tokenExpireTime());
                        logger.info("releaseLockSuccess! load new  token for key: " + key + ", token: " + token + ", expire time: " + tokenExpireTime());
                    } else {
                        logger.info("load token timeout for key: " + key);
                        if (oldToken != null) {
                            //还原token,忽略自己纠正token正确性的时间片段
                            tokenHelper.jedisCluster.set(key, oldToken);
                            logger.warn("load token timeout, reset the correct token: " + token);
                        }
                        //自己token已失效,休息一下,重新获取token
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        token = loadToken(key, timeout - 200);
                    }
                } else {
                    //休息一下
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    timeout -= 200;
                    token = tokenHelper.jedisCluster.get(key);
                }
            }
            return token;
        }

        public abstract String loadTokenImpl(String key);

        public boolean acquireLock(String keyLock) {
            long currentTime = System.currentTimeMillis();
            String threadKeyLockValue = String.valueOf(currentTime + keyLockTimeoutInMills());
            //抢锁
            if (tokenHelper.jedisCluster.setnx(keyLock, threadKeyLockValue) == 1) {
                threadLocalKeyLockValue.set(threadKeyLockValue);
                logger.info("keyLock: " + keyLock + " acquire lock success");
                return true;
            }
            //判断其他用户占用lock是否超时
            String keyLockValue = tokenHelper.jedisCluster.get(keyLock);
            //其他用户仍在占用锁
            if (keyLockValue != null) {
                //keyLock使用超时
                if (Long.parseLong(keyLockValue) < currentTime) {
                    logger.info("keyLock: " + keyLock + " timeout, try to race for it");
                    //尝试抢占锁, 忽略抢占锁之间的时间误差
                    String keyLockOldValue = tokenHelper.jedisCluster.getSet(keyLock, threadKeyLockValue);
                    //抢占成功
                    if (keyLockValue.equals(keyLockOldValue)) {
                        threadLocalKeyLockValue.set(threadKeyLockValue);
                        logger.info("acquire key lock success for a timeout usage, keyLock: " + keyLock);
                        return true;
                    }
                }
            }
            logger.info("acquire lock failed for key lock: " + keyLock);
            return false;
        }

        public boolean releaseLock(String keyLock) {
            String nowValue = tokenHelper.jedisCluster.get(keyLock);
            String threadkeyLockValue = threadLocalKeyLockValue.get();
            if (threadkeyLockValue != null) {
                //自己未超时
                if (threadkeyLockValue.equals(nowValue)) {
                    tokenHelper.jedisCluster.del(keyLock);
                    threadLocalKeyLockValue.remove();
                    return true;
                }
            }
            return false;
        }

        @Override
        public long loadTokenTimeoutInMills() {
            return DEFAULT_LOAD_TOKEN_TIMEOUT_IN_MILLS;
        }

        public long keyLockTimeoutInMills() {
            return DEFAULT_KEY_LOCK_TIMEOUT_IN_MILLS;
        }

        public int tokenExpireTime() {
            return DEFAULT_TOKEN_EXPIRE_TIIME_IN_SECOND;
        }

        public AbstractTokenLoader(TokenHelper tokenHelper) {
            this.tokenHelper = tokenHelper;
        }
    }

}
