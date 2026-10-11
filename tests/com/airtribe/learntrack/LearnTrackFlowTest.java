package com.airtribe.learntrack;

import com.airtribe.learntrack.entity.*;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.*;
import com.airtribe.learntrack.service.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

//test student, course and enrollment flows
public class LearnTrackFlowTest {
    private static int checks = 0;

    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();
        StudentService students = new StudentService(studentRepository);
        CourseService courses = new CourseService(courseRepository);
        EnrollmentService enrollments = new EnrollmentService(students, courses, enrollmentRepository);

        check(students.getAllStudents().isEmpty(), "students start empty");
        check(courses.getAllCourses().isEmpty(), "courses start empty");
        Student noEmail = new Student(50, "Sam", "Lee", "Java-01");
        check(noEmail.getEmail().isEmpty(), "overloaded constructor supplies empty email");
        Person person = noEmail;
        check(person.getDisplayName().equals("Sam Lee (Batch: Java-01)"), "polymorphic display");

        //invalid additions must not save anything or consume an ID
        try {
            students.addStudent(" ", "Rao", "", "Java-01");
            fail("blank first name accepted");
        } catch (IllegalArgumentException expected) {
            checks++;
        }
        try {
            students.addStudent("Asha", "Rao", "bad-email", "Java-01");
            fail("malformed email accepted");
        } catch (IllegalArgumentException expected) {
            checks++;
        }
        try {
            students.addStudent("Asha", "Rao", "a\tb@example.com", "Java-01");
            fail("email with internal whitespace accepted");
        } catch (IllegalArgumentException expected) {
            checks++;
        }
        try {
            students.addStudent("Asha", "Rao", null, "Java-01");
            fail("null email accepted");
        } catch (IllegalArgumentException expected) {
            checks++;
        }
        check(students.getAllStudents().isEmpty(), "invalid students not saved");

        Student student = students.addStudent(" Asha ", " Rao ", " asha@example.com ", " Java-01 ");
        Student second = students.addStudent("Sam", "Lee", "Java-01");
        check(second.getEmail().isEmpty(), "service overload allows no email");
        check(student.getId() == 1 && second.getId() == 2, "student IDs increase after validation");
        check(student.getFirstName().equals("Asha"), "student details trimmed");
        check(students.getStudentById(student.getId()) == student, "lookup returns stored object");
        try {
            students.getStudentById(999);
            fail("missing student not rejected");
        } catch (EntityNotFoundException expected) {
            checks++;
        }
        try {
            students.deactivateStudent(999);
            fail("missing student deactivation not rejected");
        } catch (EntityNotFoundException expected) {
            checks++;
        }
        try {
            students.updateStudent(student.getId(), "Changed", "Name", "bad", "Batch");
            fail("invalid update accepted");
        } catch (IllegalArgumentException expected) {
            checks++;
        }
        check(student.getFirstName().equals("Asha"), "invalid update leaves object unchanged");
        students.updateStudent(student.getId(), "Asha", "Shah", "", "Java-02");
        check(student.getLastName().equals("Shah") && student.getBatch().equals("Java-02"), "valid update saved");
        ArrayList<Student> studentCopy = students.getAllStudents();
        studentCopy.clear();
        check(students.getAllStudents().size() == 2, "student list is a structural copy");

        try {
            courses.addCourse("Java", "Basics", 0);
            fail("zero course duration accepted");
        } catch (IllegalArgumentException expected) {
            checks++;
        }
        try {
            courses.addCourse("Java", "Basics", -1);
            fail("negative course duration accepted");
        } catch (IllegalArgumentException expected) {
            checks++;
        }
        try {
            courses.addCourse(" ", "Basics", 8);
            fail("blank course name accepted");
        } catch (IllegalArgumentException expected) {
            checks++;
        }
        try {
            courses.addCourse("Java", " ", 8);
            fail("blank course description accepted");
        } catch (IllegalArgumentException expected) {
            checks++;
        }
        Course course = courses.addCourse(" Core Java ", " Java fundamentals ", 8);
        Course otherCourse = courses.addCourse("OOP", "Object orientation", 4);
        check(course.getId() == 1 && otherCourse.getId() == 2, "course counters separate from student counters");
        check(course.getCourseName().equals("Core Java"), "course details trimmed");
        try {
            courses.getCourseById(999);
            fail("missing course accepted");
        } catch (EntityNotFoundException expected) {
            checks++;
        }
        ArrayList<Course> courseCopy = courses.getAllCourses();
        courseCopy.clear();
        check(courses.getAllCourses().size() == 2, "course list is a structural copy");
        check(enrollments.getEnrollmentsForStudent(student.getId()).isEmpty(), "existing student can have empty enrollments");
        try {
            enrollments.getEnrollmentsForStudent(999);
            fail("missing student's enrollments accepted");
        } catch (EntityNotFoundException expected) {
            checks++;
        }
        try {
            enrollments.enrollStudent(999, course.getId());
            fail("enrollment for missing student accepted");
        } catch (EntityNotFoundException expected) {
            checks++;
        }
        try {
            enrollments.enrollStudent(student.getId(), 999);
            fail("enrollment for missing course accepted");
        } catch (EntityNotFoundException expected) {
            checks++;
        }

        courses.setCourseActive(course.getId(), false);
        try {
            enrollments.enrollStudent(student.getId(), course.getId());
            fail("inactive course enrollment accepted");
        } catch (IllegalArgumentException expected) {
            checks++;
        }
        courses.setCourseActive(course.getId(), true);
        students.deactivateStudent(second.getId());
        check(!second.isActive(), "student deactivated");
        try {
            enrollments.enrollStudent(second.getId(), course.getId());
            fail("inactive student enrollment accepted");
        } catch (IllegalArgumentException expected) {
            checks++;
        }

        Enrollment enrollment = enrollments.enrollStudent(student.getId(), course.getId());
        check(enrollment.getId() == 1, "rejected enrollments do not consume IDs");
        check(enrollment.getStudentID() == student.getId() && enrollment.getCourseID() == course.getId(), "IDs link enrollment correctly");
        check(enrollment.getEnrollmentDate().equals(LocalDate.now()), "enrollment dated today");
        check(enrollment.getStatus() == EnrollmentStatus.ACTIVE, "enrollment starts active");
        try {
            enrollments.enrollStudent(student.getId(), course.getId());
            fail("duplicate active enrollment accepted");
        } catch (IllegalArgumentException expected) {
            checks++;
        }
        try {
            enrollments.updateEnrollmentStatus(enrollment.getId(), null);
            fail("null status accepted");
        } catch (IllegalArgumentException expected) {
            checks++;
        }
        try {
            enrollments.updateEnrollmentStatus(enrollment.getId(), EnrollmentStatus.ACTIVE);
            fail("unsupported transition to active accepted");
        } catch (IllegalArgumentException expected) {
            checks++;
        }
        try {
            enrollments.getEnrollmentById(999);
            fail("missing enrollment accepted");
        } catch (EntityNotFoundException expected) {
            checks++;
        }
        enrollments.updateEnrollmentStatus(enrollment.getId(), EnrollmentStatus.COMPLETED);
        check(enrollment.getStatus() == EnrollmentStatus.COMPLETED, "enrollment completed");
        try {
            enrollments.updateEnrollmentStatus(enrollment.getId(), EnrollmentStatus.CANCELLED);
            fail("completed enrollment changed again");
        } catch (IllegalArgumentException expected) {
            checks++;
        }

        //a completed enrollment is retained; a new active enrollment is permitted
        Enrollment repeated = enrollments.enrollStudent(student.getId(), course.getId());
        enrollments.updateEnrollmentStatus(repeated.getId(), EnrollmentStatus.CANCELLED);
        check(repeated.getStatus() == EnrollmentStatus.CANCELLED, "enrollment cancelled");
        try {
            enrollments.updateEnrollmentStatus(repeated.getId(), EnrollmentStatus.COMPLETED);
            fail("cancelled enrollment changed again");
        } catch (IllegalArgumentException expected) {
            checks++;
        }
        enrollments.enrollStudent(student.getId(), otherCourse.getId());
        check(enrollments.getEnrollmentsForStudent(student.getId()).size() == 3, "all enrollments for student returned");
        ArrayList<Enrollment> enrollmentCopy = enrollmentRepository.getEnrollments();
        enrollmentCopy.clear();
        check(enrollmentRepository.getEnrollments().size() == 3, "enrollment list is a structural copy");
        check(enrollmentRepository.findByStudentID(999).isEmpty(), "no repository matches returns empty list");

        //check that bad number input retries instead of crashing
        Scanner numericInput = new Scanner("abc\n2147483648\n7\n");
        check(Main.readInt(numericInput, "") == 7, "invalid and overflowing numbers retry");
        numericInput.close();

        System.out.println("PASS: " + checks + " service, entity and input checks.");
    }

    private static void check(boolean condition, String description) {
        if (!condition) {
            fail(description);
        }
        checks++;
    }

    private static void fail(String description) {
        throw new AssertionError(description);
    }
}
