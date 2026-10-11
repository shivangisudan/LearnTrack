package com.airtribe.learntrack;
import com.airtribe.learntrack.entity.*;
import com.airtribe.learntrack.repository.*;
import com.airtribe.learntrack.service.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //create repositories
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

        //create services using the shared repositories
        StudentService studentService = new StudentService(studentRepository);
        CourseService courseService = new CourseService(courseRepository);
        EnrollmentService enrollmentService = new EnrollmentService(
                studentService, courseService, enrollmentRepository
        );

        //use one scanner for all console input
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        //keep showing the menu until the user exits
        while (running) {
            System.out.println("\n--- LearnTrack ---");
            System.out.println("1. Students");
            System.out.println("2. Courses");
            System.out.println("3. Enrollments");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            //exit cleanly if console input ends
            if (!scanner.hasNextLine()) {
                break;
            }

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                //handle the selected menu option
                switch (choice) {
                    case 1:
                        System.out.println("Student management selected");
                        break;
                    case 2:
                        System.out.println("Course management selected");
                        break;
                    case 3:
                        System.out.println("Enrollment management selected");
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            } catch (NumberFormatException e) {
                //handle text or numbers outside the int range
                System.out.println("Please enter a number.");
            }
        }

        scanner.close();
        System.out.println("Goodbye!");

    }
}