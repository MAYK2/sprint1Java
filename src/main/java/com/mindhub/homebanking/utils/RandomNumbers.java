package com.mindhub.homebanking.utils;


import java.util.Random;


public class RandomNumbers {
        public static String generateCardNumber() {
        Random random = new Random();
        return String.format("%04d-%04d-%04d-%04d",
                random.nextInt(10000),
                random.nextInt(10000),
                random.nextInt(10000),
                random.nextInt(10000));
    }

    public static int eightDigits() {
        Random random = new Random();
        return random.nextInt(100000000);
    }
    public static int threeDigits() {
        Random random = new Random();
        return random.nextInt(900) + 100; // Genera un número entre 100 y 999
    }
    public static int singleDigit() {
        Random random = new Random();
        return random.nextInt(10); // Generates a single digit number (0 to 9)
    }
}
