package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IDGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;

//course management
public class CourseService {
    private CourseRepository courseRepository;

    //constructor
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    //add a course
    public Course addCourse(String courseName, String courseDescription,
                            int courseDuration) {
        InputValidator.requireNonBlank(courseName, "Course name");
        InputValidator.requireNonBlank(courseDescription, "Course description");
        InputValidator.requirePositive(courseDuration, "Course duration");

        Course course = new Course(
                IDGenerator.getNextCourseId(),
                courseName.trim(),
                courseDescription.trim(),
                courseDuration
        );

        courseRepository.addCourse(course);
        return course;
    }

    //find course by ID & error handling
    public Course getCourseById(int id) {
        Course course = courseRepository.findCourseByID(id);

        if (course == null) {
            throw new EntityNotFoundException(
                    "Course with ID " + id + " not found"
            );
        }

        return course;
    }

    //list all courses
    public ArrayList<Course> getAllCourses() {
        return courseRepository.getCourses();
    }

    //activate or deactivate a course
    public void setCourseActive(int id, boolean active) {
        Course course = getCourseById(id);
        course.setActive(active);
    }
}
