package com.airtribe.learntrack;

import com.airtribe.learntrack.entity.Student;

public class Main {
    public static void main(String[] args) {
        Student student = new Student(1, "Asha", "Rao","asharao@gmail.com","Java01");
        System.out.println(student.getDisplayName());
    }
}