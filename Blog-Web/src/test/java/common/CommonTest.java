package common;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CommonTest {

    public Map<Character, String> getCodeMap() {
        Map<Character, String> map = new HashMap<>(52);
        char c = 'a';
        for (int i = 2; i <= 9; i++) {
            int k = (i == 7 || i == 9) ? 4 : 3;
            for (int j = 1; j <= k; j++) {
                String value = "" + i + j;
                map.put(c, value);
                c++;
            }
        }
        map.put(' ', "11");
        map.put(',', "12");
        map.put('.', "13");
        for (c = '0'; c <= '9'; c++) {
            String value = c + "0";
            map.put(c, value);
        }
        //System.out.println(map);
        return map;
    }

    @Test
    public void testEncode() {
        Map<Character, String> map = getCodeMap();
        String msg = "no zuo no die.";
        StringBuilder sb = new StringBuilder(msg.length() * 2);
        for (int i = 0; i < msg.length(); i++) {
            String value = map.get(msg.charAt(i));
            if (value != null) {
                sb.append(value);
            } else {
                System.out.println("包含不能识别的字符");
                break;
            }
        }
        if (sb.length() == msg.length() * 2) {
            System.out.println("原文：" + msg);
            System.out.println("密文：" + sb.toString());
        }
        System.out.println("原文：" + msg);
    }


}
