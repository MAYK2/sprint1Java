package com.mindhub.homebanking.utils;

public final class CardUtils {

    private CardUtils() {
    }

    public static String generateCardNumber() {
        return RandomNumbers.generateCardNumber();
    }

    public static int getCVV() {
        return RandomNumbers.threeDigits();
    }
    public static String getCardNumber() {
        StringBuilder cardNumberBuilder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int digit = RandomNumbers.singleDigit();
            cardNumberBuilder.append(digit);
        }
        return cardNumberBuilder.toString();
    }

}
