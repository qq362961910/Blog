package token;

import org.junit.Test;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class GeneralTest {

    @Test
    public void testRandom() {
        Random r = new Random();
        for (int i = 0; i < 10000; i++) {
            int num = r.nextInt();
            if (num < 0) {
                System.out.println(num);
            }
        }
    }

    @Test
    public void testModel() {
        System.out.println(10 % (-3));
    }


    public static void main(String[] args) {
        final Map<Integer, Integer> map = new ConcurrentHashMap<>();

        new Thread() {
            @Override
            public void run() {
                int index = 0;
                while (true) {
                    try {
                        for (int i = 0; i < 10; i++) {
                            map.put(i, index++);
                        }
//                        for (int i=0; i<10; i++) {
//                            map.remove(i);
//                            Thread.sleep(200);
//                        }
                        Thread.sleep(30000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                            System.out.println("key :" + entry.getKey() + ", value: " + entry.getValue());
                            Thread.sleep(500);
                            map.remove(entry.getKey());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
