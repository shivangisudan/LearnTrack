package com.airtribe.learntrack.util;

//separate counters for each type of record
public class IDGenerator {
    private static int studentIdCounter = 0;
    private static int courseIdCounter = 0;
    private static int enrollmentIdCounter = 0;

    //next student ID
    public static int getNextStudentId() {
        studentIdCounter = studentIdCounter + 1;
        return studentIdCounter;
    }
    //next course ID
    public static int getNextCourseId() {
        courseIdCounter = courseIdCounter + 1;
        return courseIdCounter;
    }
    //next enrollment ID
    public static int getNextEnrollmentId() {
        enrollmentIdCounter = enrollmentIdCounter + 1;
        return enrollmentIdCounter;
    }
}
