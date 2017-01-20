
import com.jy.helper.TokenHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TokenHelperTest {

    private static final Logger logger = LogManager.getLogger(TokenHelperTest.class);
    private static final int TOTAL_WORKER = 20;
    private static final String[] keys = {"AAA", "BBB", "CCC", "DDD"};
    private static final int MIN_WORK_TIME = 100;
    private static final int MAX_WORK_TIME = 10000;
    private static ExecutorService ExecutorService = Executors.newFixedThreadPool(50);


    public static void main(String[] args) {

        logger.info("test begin....");
        //prepare TokenHelper
        TokenHelper tokenHelper = new TokenHelper(3000, 1000, new TokenHelper.TokenLoader() {
            @Override
            public String loadToken(String key) {
                return Math.random() + "";
            }
        });

        //prepare Worker List
        List<Worker> workerList = new ArrayList<>(TOTAL_WORKER);
        for (int i=0; i<TOTAL_WORKER; i++) {
            workerList.add(WorkerFactory.newRandomWorker(keys[i%keys.length], tokenHelper));
        }

        //begin to work
        for (int i=0; i<30; i++) {
            for (final Worker worker: workerList) {
                final int worktime = RandonUtil.nextRandomInt(MIN_WORK_TIME, MAX_WORK_TIME);
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
    }
}


class WorkerFactory {
    private static final String WORKER_NAME_PREFIX = "worker";
    private static int count = 0;
    public static Worker newRandomWorker(String key, TokenHelper tokenHelper) {
        return new Worker(WORKER_NAME_PREFIX + "-" + count++, key, tokenHelper);
    }
}

class Worker {
    private static final Logger logger = LogManager.getLogger(Worker.class);
    private final String workerName;
    private final String key;
    private final TokenHelper tokenHelper;

    public boolean work(int workTimeInMills) {
        String token = tokenHelper.getToken(key);

        try {
            Thread.sleep(workTimeInMills);
            if (tokenHelper.getToken(key).equals(token)) {
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

    public Worker(String workerName, String key, TokenHelper tokenHelper) {
        this.workerName = workerName;
        this.key = key;
        this.tokenHelper = tokenHelper;
    }

    public String getWorkerName() {
        return workerName;
    }
}

class RandonUtil {

    private static  final Random random = new Random();

    public static int nextRandomInt(int min, int max) {
        int r = random.nextInt();
        r%=max;
        if (r > min) {
            return max;
        }
        else {
            return r + (max - min);
        }
    }
}
