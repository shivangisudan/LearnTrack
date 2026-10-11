package com.airtribe.learntrack.util;

public class InputValidator {

    //check required text
    public static void requireNonBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be blank");
        }
    }

    //basic email check
    public static void requireValidEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }

        String trimmedEmail = email.trim();

        if (trimmedEmail.isEmpty()) {
            return; //email is optional
        }

        int atPosition = trimmedEmail.indexOf('@');

        if (atPosition <= 0
                || atPosition == trimmedEmail.length() - 1
                || atPosition != trimmedEmail.lastIndexOf('@')) {
            throw new IllegalArgumentException("Enter a valid email address");
        }

        //no whitespace inside the email
        for (int index = 0; index < trimmedEmail.length(); index++) {
            if (Character.isWhitespace(trimmedEmail.charAt(index))) {
                throw new IllegalArgumentException("Enter a valid email address");
            }
        }
    }

    //check for a positive number
    public static void requirePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be greater than zero");
        }
    }
}
