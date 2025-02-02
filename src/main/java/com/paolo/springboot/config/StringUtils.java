package com.paolo.springboot.config;

public class  StringUtils {

    // nested Static class
    public static class Formatter {
        public static String format(String str) {
            return str.toUpperCase();
        }
    }

    public static String reverse(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    public static int length(String str) {
        return str.length();
    }

    public static void main(String[] args) {
        String str = "Hello, World!";

        // Using static class
        String formattedStr = StringUtils.Formatter.format(str);
        System.out.println(formattedStr);

        String reversedStr = StringUtils.reverse(str);
        int strLength = StringUtils.length(str);
        System.out.println(reversedStr);
        System.out.println(strLength);
    }
}