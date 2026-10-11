package com.airtribe.learntrack.util;

public class IDGenerator {
    private static int studentIdCounter = 0;

    public static int getNextStudentId() {
        studentIdCounter = studentIdCounter + 1;
        return studentIdCounter;
    }
}