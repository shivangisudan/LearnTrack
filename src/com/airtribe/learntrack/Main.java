package com.airtribe.learntrack;

import com.airtribe.learntrack.entity.Enrollment;

public class Main {
    public static void main(String[] args) {
        Enrollment enrollment = new Enrollment(1, 1, 101);
        System.out.println(enrollment.getEnrollmentDate());
        System.out.println(enrollment.getStatus());

        enrollment.setStatus("COMPLETED");
        System.out.println(enrollment.getStatus());
    }
}