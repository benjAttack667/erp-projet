package com.minierp.utils;


import java.security.MessageDigest;

public class HashUtil {

    public static String hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] b = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte x : b) sb.append(String.format("%02x", x));
            return sb.toString();
        } catch (Exception e) { return null; }
    }

    public static boolean verify(String input, String hashed) {
        return hash(input).equals(hashed);
    }
}