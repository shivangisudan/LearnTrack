package com.airtribe.learntrack.entity;

import java.time.LocalDate;

public class Enrollment {
    private int id;
    private int studentID;
    private int courseID;
    private LocalDate enrollmentDate;
    private String status;

    //constructor
    public Enrollment(int id, int studentID, int courseID) {
        this.id = id;
        this.studentID = studentID;
        this.courseID = courseID;

        this.enrollmentDate = LocalDate.now();
        this.status = "ACTIVE";
    }

    //getters
    public int getId() {
        return id;
    }
    public int getStudentID() {
        return studentID;
    }
    public int getCourseID() {
        return courseID;
    }
    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }
    public String getStatus() {
        return status;
    }

    //setters
    public void setStatus(String status) {
        this.status = status;
    }
}
