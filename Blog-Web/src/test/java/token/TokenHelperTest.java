package token;

import com.jy.helper.TokenHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class TokenHelperTest {

    private static final Logger logger = LogManager.getLogger(TokenHelperTest.class);
    private static volatile Server server = new Server();
    private static final int TOTAL_WORKER = 20;
    private static final String[] keys = {"AAA", "BBB", "CCC", "DDD"};
    private static final int MIN_WORK_TIME = 100;
    private static final int MAX_WORK_TIME = 10000;
    private static ExecutorService ExecutorService = Executors.newFixedThreadPool(30);


    public static void main(String[] args) throws Exception{

        logger.info("test begin....");
        //prepare TokenHelper
        TokenHelper tokenHelper = new TokenHelper(30000, 15000, new TokenHelper.TokenLoader() {
            @Override
            public String loadToken(String key) {
                return server.getToken(key);
            }
        });

        //prepare Worker List
        List<Worker> workerList = new ArrayList<>(TOTAL_WORKER);
        for (int i=0; i<TOTAL_WORKER; i++) {
            workerList.add(WorkerFactory.newRandomWorker(keys[i%keys.length], tokenHelper, server));
        }

        //begin to work
        for (int i=0; i<10; i++) {
            for (final Worker worker: workerList) {
                final int worktime = RandonUtil.nextRandomPositiveInt(MIN_WORK_TIME, MAX_WORK_TIME);
                ExecutorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        worker.work(worktime);
                    }
                });
            }
        }
        //relase resource
        ExecutorService.shutdown();
        boolean success = ExecutorService.awaitTermination(5, TimeUnit.MINUTES);
        if (success) {
            logger.info("ExecutorService close success");
        }
        else {
            logger.info("ExecutorService close fail");
        }
        tokenHelper.close();
        logger.info("tokenHelper close....");
        server.destroy();
        logger.info("server close....");
    }
}


class WorkerFactory {
    private static final String WORKER_NAME_PREFIX = "worker";
    private static int count = 0;
    public static Worker newRandomWorker(String key, TokenHelper tokenHelper, Server server) {
        return new Worker(WORKER_NAME_PREFIX + "-" + count++, key, tokenHelper, server);
    }
}

class Worker {
    private static final Logger logger = LogManager.getLogger(Worker.class);
    private final String workerName;
    private final String key;
    private final TokenHelper tokenHelper;
    private volatile Server server;

    public boolean work(int workTimeInMills) {
        String token = tokenHelper.getToken(key);
        try {
            Thread.sleep(workTimeInMills);
            boolean success = server.service(token);
            if (success) {
                logger.info("worker: " + workerName + " get token: " + token + ", will work " + workTimeInMills + " million seconds with key: " + key);
            }
            else {
                logger.error("worker: " + workerName + " with wrong token: " + token + ", key: " + key);
            }
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }finally {
            tokenHelper.releaseToken(key);
        }
    }

    public Worker(String workerName, String key, TokenHelper tokenHelper, Server server) {
        this.workerName = workerName;
        this.key = key;
        this.tokenHelper = tokenHelper;
        this.server = server;
    }

    public String getWorkerName() {
        return workerName;
    }
}

class RandonUtil {

    private static  final Random random = new Random();

    public static int nextRandomPositiveInt(int min, int max) {
        int r = random.nextInt();
        if (r<0) {
            r = -r;
        }
        r%=max;
        if (r >= min && r<=max) {
            return r;
        }
        else {
            return min + (r%(max - min));
        }
    }
}


class Server {

    private static final long TOKEN_EXPIRE_TIME_IN_MILLS = 1000 * 60 * 120;
//    private static final long TOKEN_EXPIRE_TIME_IN_MILLS = 1000 * 4;
    private static final long CHECK_TOKEN_EXPIRE_TIME_IN_MILLS = 500;

    private static final Object lock = new Object();
    private static final Logger logger = LogManager.getLogger(Server.class);

    private static final Map<String, String> CACHE_TOKEN = new HashMap<>();
    private static final Map<String, Long> TOKEN_MARK = new ConcurrentHashMap<>();
    private static volatile boolean close = false;

    public String getToken(String key) {
        String uuid = UUID.randomUUID().toString();
        synchronized (lock) {
            CACHE_TOKEN.put(uuid, key);
        }
        TOKEN_MARK.put(key, System.currentTimeMillis());
        logger.info("key: " + key + ", change token: " + uuid);
        return uuid;
    }

    public boolean service(String token) {
        String value = CACHE_TOKEN.get(token);
        if (value == null) {
            logger.error("--------------------------------------ERROR--------------------------------------");
            return false;
        }
        return true;
    }

    public Server() {
        new Thread(){
            @Override
            public void run() {
                while (!close) {
                    try {
                        Set<Map.Entry<String, Long>> entries = TOKEN_MARK.entrySet();
                        Iterator<Map.Entry<String, Long>> it = entries.iterator();
                        while (it.hasNext()) {
                            Map.Entry<String, Long> entry = it.next();
                            Long value = entry.getValue();
                            //如果过时
                            if (System.currentTimeMillis() - TOKEN_EXPIRE_TIME_IN_MILLS > value) {
                                CACHE_TOKEN.remove(entry.getKey());
                            }
                        }
                        Thread.sleep(CHECK_TOKEN_EXPIRE_TIME_IN_MILLS);
                    } catch (Exception e) {
                        logger.error("遍历检查缓存token是否过期失败");
                        logger.error(e.toString());
                    }
                }
            }
        }.start();
    }
    public void destroy() {
        close = true;
    }
}
