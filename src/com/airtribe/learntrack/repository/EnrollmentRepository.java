package com.airtribe.learntrack.repository;
import com.airtribe.learntrack.entity.Enrollment;
import java.util.ArrayList;

public class EnrollmentRepository {
    private ArrayList<Enrollment> enrollments = new ArrayList<>();

    //add enrollments to the list
    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    //get all enrollments in a list
    public ArrayList<Enrollment> getEnrollments() {
        return new ArrayList<>(enrollments);
    }

    //find an enrollment by its ID
    public Enrollment getEnrollmentById(int id) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == id){
                return enrollment;
            }
        }
        return null;
    }

    //find student's enrollments by ID

    public ArrayList<Enrollment> findByStudentID(int studentID){
        ArrayList<Enrollment> results = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentID() == studentID){
                results.add(enrollment);
            }
        }
        return results;
    }

}
