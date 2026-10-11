package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IDGenerator;

import java.util.ArrayList;

//enrollment management
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

    //enroll a student in a course
    public Enrollment enrollStudent(int studentId, int courseId) {
        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourseById(courseId);

        //check that both records are active
        if (!student.isActive()) {
            throw new IllegalArgumentException("Student is inactive");
        }

        if (!course.isActive()) {
            throw new IllegalArgumentException("Course is inactive");
        }

        //check for an active enrollment in the same course
        for (Enrollment enrollment :
                enrollmentRepository.findByStudentID(studentId)) {
            if (enrollment.getCourseID() == courseId
                    && enrollment.getStatus() == EnrollmentStatus.ACTIVE) {
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

    //find enrollment by ID & error handling
    public Enrollment getEnrollmentById(int id) {
        Enrollment enrollment = enrollmentRepository.getEnrollmentById(id);

        if (enrollment == null) {
            throw new EntityNotFoundException(
                    "Enrollment with ID " + id + " not found"
            );
        }

        return enrollment;
    }

    //list a student's enrollments
    public ArrayList<Enrollment> getEnrollmentsForStudent(int studentId) {
        studentService.getStudentById(studentId);
        return enrollmentRepository.findByStudentID(studentId);
    }

    //complete or cancel an active enrollment
    public void updateEnrollmentStatus(int id, EnrollmentStatus status) {
        //check the requested status
        if (status != EnrollmentStatus.COMPLETED && status != EnrollmentStatus.CANCELLED) {
            throw new IllegalArgumentException(
                    "Status must be COMPLETED or CANCELLED"
            );
        }

        Enrollment enrollment = getEnrollmentById(id);

        //only active enrollments can change status
        if (enrollment.getStatus() != EnrollmentStatus.ACTIVE) {
            throw new IllegalArgumentException(
                    "Only active enrollments can be completed or cancelled"
            );
        }

        enrollment.setStatus(status);
    }
}
