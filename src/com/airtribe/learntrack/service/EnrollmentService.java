package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IDGenerator;

import java.util.ArrayList;

//checks enrollment rules and uses the repository to store enrollments
public class EnrollmentService {
    private StudentService studentService;
    private CourseService courseService;
    private EnrollmentRepository enrollmentRepository;

    //constructor
    public EnrollmentService(StudentService studentService,
                             CourseService courseService,
                             EnrollmentRepository enrollmentRepository) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.enrollmentRepository = enrollmentRepository;
    }

    //validate the student and course, then create an enrollment
    public Enrollment enrollStudent(int studentId, int courseId) {
        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourseById(courseId);

        //inactive students and courses cannot receive new enrollments
        if (!student.isActive()) {
            throw new IllegalArgumentException("Student is inactive");
        }

        if (!course.isActive()) {
            throw new IllegalArgumentException("Course is inactive");
        }

        //prevent duplicate active enrollments for the same student and course
        for (Enrollment enrollment :
                enrollmentRepository.findByStudentID(studentId)) {
            if (enrollment.getCourseID() == courseId
                    && "ACTIVE".equals(enrollment.getStatus())) {
                throw new IllegalArgumentException(
                        "Student is already enrolled in this course"
                );
            }
        }

        Enrollment enrollment = new Enrollment(
                IDGenerator.getNextEnrollmentId(),
                studentId,
                courseId
        );

        enrollmentRepository.addEnrollment(enrollment);
        return enrollment;
    }

    //find an enrollment by ID and handle a missing enrollment
    public Enrollment getEnrollmentById(int id) {
        Enrollment enrollment = enrollmentRepository.getEnrollmentById(id);

        if (enrollment == null) {
            throw new EntityNotFoundException(
                    "Enrollment with ID " + id + " not found"
            );
        }

        return enrollment;
    }

    //check that the student exists, then list their enrollments
    public ArrayList<Enrollment> getEnrollmentsForStudent(int studentId) {
        studentService.getStudentById(studentId);
        return enrollmentRepository.findByStudentID(studentId);
    }

    //complete or cancel an active enrollment
    public void updateEnrollmentStatus(int id, String status) {
        //accept only the two supported final statuses
        if (!"COMPLETED".equals(status) && !"CANCELLED".equals(status)) {
            throw new IllegalArgumentException(
                    "Status must be COMPLETED or CANCELLED"
            );
        }

        Enrollment enrollment = getEnrollmentById(id);

        //completed or cancelled enrollments cannot be changed again
        if (!"ACTIVE".equals(enrollment.getStatus())) {
            throw new IllegalArgumentException(
                    "Only active enrollments can be completed or cancelled"
            );
        }

        enrollment.setStatus(status);
    }
}
