package com.chatapp.chatservice.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {

    public static String hashString(String s) throws NoSuchAlgorithmException {
        try {
            String salt = "customsalt";
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(hexStringToBytes(salt));
            byte[] hashedPasswordBytes = md.digest(s.getBytes());

            return bytesToHex(hashedPasswordBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException();
        }
    }

    private static String bytesToHex(byte[] saltBytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : saltBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Helper method to convert a hexadecimal string to a byte array
    private static byte[] hexStringToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) +
                    Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }
}
