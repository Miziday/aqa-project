package utils;

import java.security.SecureRandom;
import java.util.UUID;

public class RandomData {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom rand = new SecureRandom();

    public static String getRandomString(int length) {
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(CHARACTERS.length());
            result.append(CHARACTERS.charAt(index));
        }
        return result.toString();
    }

    public static int getRandomNumber(int min, int max) {
        return rand.nextInt(min, max);
    }

    public static String getRandUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getRandEmail() {
        return getRandomString(10) + "@mail.ru";
    }

}
