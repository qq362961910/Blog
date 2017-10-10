package com.jy.util;

import java.util.Random;

public class RandomUtil {

    private static final Random random = new Random();
    private char[] chars = {'0','1','2','3','4','5','6','7','8','9'};

    public static String getRandomStr() {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 16; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String randomNumberString(String seed, byte length) {
        StringBuilder sb = new StringBuilder(length);
        for (byte i=0; i<length; i++) {
            sb.append(random.nextInt(10)%10);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        for (int i=0; i<10; i++) {
            System.out.println(randomNumberString("17600369745", (byte)6));
        }
    }
}
