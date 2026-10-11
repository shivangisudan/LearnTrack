package com.airtribe.learntrack.entity;

import java.time.LocalDate;
import com.airtribe.learntrack.enums.EnrollmentStatus;

public class Enrollment {
    private int id;
    private int studentID;
    private int courseID;
    private LocalDate enrollmentDate;
    private EnrollmentStatus status;

    //constructor
    public Enrollment(int id, int studentID, int courseID) {
        this.id = id;
        this.studentID = studentID;
        this.courseID = courseID;

        this.enrollmentDate = LocalDate.now();
        this.status = EnrollmentStatus.ACTIVE;
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
    public EnrollmentStatus getStatus() {
        return status;
    }

    //setters
    public void setStatus(EnrollmentStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Enrollment status cannot be null");
        }
        this.status = status;
    }
}
