package com.jy.util;

public class StringUtil {

    public static boolean isEmpty(String content) {
        return content == null || content.trim().length() == 0;
    }

    public static class StringPart {
        private char[] latinCharacters;
        private char[] otherCharacters;

        public char[] getLatinCharacters() {
            return latinCharacters;
        }

        public StringPart setLatinCharacters(char[] latinCharacters) {
            this.latinCharacters = latinCharacters;
            return this;
        }

        public char[] getOtherCharacters() {
            return otherCharacters;
        }

        public StringPart setOtherCharacters(char[] otherCharacters) {
            this.otherCharacters = otherCharacters;
            return this;
        }
    }

    public static StringPart partUTF8String(String src) {

        byte[] bytes = src.getBytes();

        StringPart stringPart = new StringPart();

        StringBuilder latinCharactersBuilder = new StringBuilder();
        StringBuilder otherCharactersBuilder = new StringBuilder();

        int cursor = 0;

        while (cursor < bytes.length) {
            byte buff = bytes[cursor];

            int offset = 0;
            while (offset < 8) {
                if (((buff >> (7 - offset)) & 1) != 1) {
                    break;
                }
                offset += 1;
            }
            int numBytes = (offset == 0 || offset == 1) ? 1 : offset;

            if (numBytes == 1) {
                latinCharactersBuilder.append((char) bytes[cursor]);
            } else {
                byte[] tmp = new byte[numBytes];
                System.arraycopy(bytes, cursor, tmp, 0, numBytes);
                otherCharactersBuilder.append(new String(tmp));
            }

            cursor += numBytes;
        }
        return stringPart
                .setLatinCharacters(latinCharactersBuilder.toString().toCharArray())
                .setOtherCharacters(otherCharactersBuilder.toString().toCharArray());
    }

    public static void main(String[] args) {
//        String text = "撒大手\n大脚反馈\n\n\n";
//        System.out.println(text);
        String test = "�Ļ���-�Ļ���-�Զ��\uD8BA\uDFE3����ˣ�-δ��ע̽����(̽������ţ�00001119 ̽�������ͣ�)";
        StringPart stringPart = partUTF8String(test);
        System.out.println(String.format("latin: %s", new String(stringPart.getLatinCharacters())));
        System.out.println(String.format("other: %s", new String(stringPart.getOtherCharacters())));
    }
}
