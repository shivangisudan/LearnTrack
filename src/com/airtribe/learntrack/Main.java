package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.*;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.*;
import com.airtribe.learntrack.service.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

//menu and application entry point
public class Main {
    public static void main(String[] args) {
        //create repositories and services
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();
        StudentService studentService = new StudentService(studentRepository);
        CourseService courseService = new CourseService(courseRepository);
        EnrollmentService enrollmentService = new EnrollmentService(
                studentService, courseService, enrollmentRepository
        );

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to " + AppConstants.APP_NAME + "!");
        System.out.println(AppConstants.DATA_NOTICE);

        //start the menu
        try {
            runMainMenu(scanner, studentService, courseService, enrollmentService);
        } catch (NoSuchElementException e) {
            System.out.println("\nInput ended. Closing LearnTrack.");
        } finally {
            scanner.close();
        }
        System.out.println("Goodbye!");
    }

    //main menu
    private static void runMainMenu(Scanner scanner, StudentService studentService,
                                    CourseService courseService,
                                    EnrollmentService enrollmentService) {
        while (true) {
            System.out.println("\n--- " + AppConstants.APP_NAME + " ---");
            System.out.println("1. Students\n2. Courses\n3. Enrollments\n0. Exit");
            int choice = readInt(scanner, "Choose an option: ");
            switch (choice) {
                case MenuOptions.STUDENTS:
                    runStudentMenu(scanner, studentService);
                    break;
                case MenuOptions.COURSES:
                    runCourseMenu(scanner, courseService);
                    break;
                case MenuOptions.ENROLLMENTS:
                    runEnrollmentMenu(scanner, enrollmentService, courseService);
                    break;
                case MenuOptions.BACK_OR_EXIT:
                    return;
                default:
                    System.out.println(AppConstants.INVALID_OPTION);
            }
        }
    }

    //student menu
    private static void runStudentMenu(Scanner scanner, StudentService service) {
        while (true) {
            System.out.println("\n--- Students ---");
            System.out.println("1. Add student\n2. List students\n3. Find student by ID");
            System.out.println("4. Update student\n5. Deactivate student\n0. Back");
            int choice = readInt(scanner, "Choose an option: ");
            try {
                switch (choice) {
                    case MenuOptions.ADD_STUDENT:
                        addStudent(scanner, service);
                        break;
                    case MenuOptions.LIST_STUDENTS:
                        listStudents(service);
                        break;
                    case MenuOptions.FIND_STUDENT:
                        printStudent(service.getStudentById(readInt(scanner, "Student ID: ")));
                        break;
                    case MenuOptions.UPDATE_STUDENT:
                        updateStudent(scanner, service);
                        break;
                    case MenuOptions.DEACTIVATE_STUDENT:
                        service.deactivateStudent(readInt(scanner, "Student ID: "));
                        System.out.println("Student deactivated.");
                        break;
                    case MenuOptions.BACK_OR_EXIT:
                        return;
                    default:
                        System.out.println(AppConstants.INVALID_OPTION);
                }
            } catch (EntityNotFoundException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    //add a student
    private static void addStudent(Scanner scanner, StudentService service) {
        String firstName = readText(scanner, "First name: ");
        String lastName = readText(scanner, "Last name: ");
        String email = readText(scanner, "Email (optional): ");
        String batch = readText(scanner, "Batch: ");
        Student student = service.addStudent(firstName, lastName, email, batch);
        System.out.println("Student added. ID: " + student.getId());
        printStudent(student);
    }

    //update student details
    private static void updateStudent(Scanner scanner, StudentService service) {
        int id = readInt(scanner, "Student ID: ");
        printStudent(service.getStudentById(id));
        System.out.println("Enter replacement details for all fields.");
        String firstName = readText(scanner, "First name: ");
        String lastName = readText(scanner, "Last name: ");
        String email = readText(scanner, "Email (optional): ");
        String batch = readText(scanner, "Batch: ");
        service.updateStudent(id, firstName, lastName, email, batch);
        System.out.println("Student updated.");
        printStudent(service.getStudentById(id));
    }

    //list all students
    private static void listStudents(StudentService service) {
        ArrayList<Student> students = service.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student student : students) {
            printStudent(student);
        }
    }

    //display student info
    private static void printStudent(Student student) {
        Person person = student; //calls Student's display method
        System.out.println("ID: " + person.getId() + " | " + person.getDisplayName()
                + " | Email: " + student.getEmail() + " | Active: " + student.isActive());
    }

    //course menu
    private static void runCourseMenu(Scanner scanner, CourseService service) {
        while (true) {
            System.out.println("\n--- Courses ---");
            System.out.println("1. Add course\n2. List courses\n3. Find course by ID");
            System.out.println("4. Activate course\n5. Deactivate course\n0. Back");
            int choice = readInt(scanner, "Choose an option: ");
            try {
                switch (choice) {
                    case MenuOptions.ADD_COURSE:
                        addCourse(scanner, service);
                        break;
                    case MenuOptions.LIST_COURSES:
                        listCourses(service);
                        break;
                    case MenuOptions.FIND_COURSE:
                        printCourse(service.getCourseById(readInt(scanner, "Course ID: ")));
                        break;
                    case MenuOptions.ACTIVATE_COURSE:
                        service.setCourseActive(readInt(scanner, "Course ID: "), true);
                        System.out.println("Course activated.");
                        break;
                    case MenuOptions.DEACTIVATE_COURSE:
                        service.setCourseActive(readInt(scanner, "Course ID: "), false);
                        System.out.println("Course deactivated.");
                        break;
                    case MenuOptions.BACK_OR_EXIT:
                        return;
                    default:
                        System.out.println(AppConstants.INVALID_OPTION);
                }
            } catch (EntityNotFoundException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    //add a course
    private static void addCourse(Scanner scanner, CourseService service) {
        String name = readText(scanner, "Course name: ");
        String description = readText(scanner, "Description: ");
        int weeks = readInt(scanner, "Duration in weeks: ");
        Course course = service.addCourse(name, description, weeks);
        System.out.println("Course added. ID: " + course.getId());
        printCourse(course);
    }

    //list all courses
    private static void listCourses(CourseService service) {
        ArrayList<Course> courses = service.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        for (Course course : courses) {
            printCourse(course);
        }
    }

    //display course info
    private static void printCourse(Course course) {
        System.out.println("ID: " + course.getId() + " | " + course.getCourseName()
                + " | " + course.getCourseDescription()
                + " | Duration: " + course.getCourseDuration() + " weeks"
                + " | Active: " + course.isActive());
    }

    //enrollment menu
    private static void runEnrollmentMenu(Scanner scanner, EnrollmentService service,
                                          CourseService courseService) {
        while (true) {
            System.out.println("\n--- Enrollments ---");
            System.out.println("1. Enroll student\n2. List enrollments for student\n3. Find enrollment by ID");
            System.out.println("4. Complete enrollment\n5. Cancel enrollment\n0. Back");
            int choice = readInt(scanner, "Choose an option: ");
            try {
                switch (choice) {
                    case MenuOptions.ENROLL_STUDENT:
                        enrollStudent(scanner, service, courseService);
                        break;
                    case MenuOptions.LIST_STUDENT_ENROLLMENTS:
                        listEnrollments(scanner, service, courseService);
                        break;
                    case MenuOptions.FIND_ENROLLMENT:
                        printEnrollment(service.getEnrollmentById(readInt(scanner, "Enrollment ID: ")),
                                courseService);
                        break;
                    case MenuOptions.COMPLETE_ENROLLMENT:
                        service.updateEnrollmentStatus(readInt(scanner, "Enrollment ID: "),
                                EnrollmentStatus.COMPLETED);
                        System.out.println("Enrollment completed.");
                        break;
                    case MenuOptions.CANCEL_ENROLLMENT:
                        service.updateEnrollmentStatus(readInt(scanner, "Enrollment ID: "),
                                EnrollmentStatus.CANCELLED);
                        System.out.println("Enrollment cancelled.");
                        break;
                    case MenuOptions.BACK_OR_EXIT:
                        return;
                    default:
                        System.out.println(AppConstants.INVALID_OPTION);
                }
            } catch (EntityNotFoundException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    //enroll a student in a course
    private static void enrollStudent(Scanner scanner, EnrollmentService service,
                                      CourseService courseService) {
        int studentId = readInt(scanner, "Student ID: ");
        int courseId = readInt(scanner, "Course ID: ");
        Enrollment enrollment = service.enrollStudent(studentId, courseId);
        System.out.println("Enrollment created. ID: " + enrollment.getId());
        printEnrollment(enrollment, courseService);
    }

    //list a student's enrollments
    private static void listEnrollments(Scanner scanner, EnrollmentService service,
                                        CourseService courseService) {
        int studentId = readInt(scanner, "Student ID: ");
        ArrayList<Enrollment> enrollments = service.getEnrollmentsForStudent(studentId);
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found for this student.");
            return;
        }
        for (Enrollment enrollment : enrollments) {
            printEnrollment(enrollment, courseService);
        }
    }

    //display enrollment info
    private static void printEnrollment(Enrollment enrollment, CourseService courseService) {
        Course course = courseService.getCourseById(enrollment.getCourseID());
        System.out.println("ID: " + enrollment.getId()
                + " | Student ID: " + enrollment.getStudentID()
                + " | Course ID: " + enrollment.getCourseID()
                + " | Course: " + course.getCourseName()
                + " | Date: " + enrollment.getEnrollmentDate()
                + " | Status: " + enrollment.getStatus());
    }

    //read a whole line of input
    private static String readText(Scanner scanner, String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            throw new NoSuchElementException("Console input ended");
        }
        return scanner.nextLine().trim();
    }

    //read a number and retry invalid input
    static int readInt(Scanner scanner, String prompt) {
        while (true) {
            String input = readText(scanner, prompt);
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(AppConstants.INVALID_NUMBER);
            }
        }
    }
}
