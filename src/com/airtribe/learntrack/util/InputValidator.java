package com.airtribe.learntrack.util;

public class InputValidator {

    public static void requireNonBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank");
        }
    }

    public static void requireValidEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }

        String trimmedEmail = email.trim();

        if (trimmedEmail.isEmpty()) {
            return; // Email is optional.
        }

        int atPosition = trimmedEmail.indexOf('@');

        if (atPosition <= 0
                || atPosition == trimmedEmail.length() - 1
                || atPosition != trimmedEmail.lastIndexOf('@')
                || trimmedEmail.contains(" ")) {
            throw new IllegalArgumentException("Enter a valid email address");
        }
    }

    public static void requirePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be greater than zero");
        }
    }
}
