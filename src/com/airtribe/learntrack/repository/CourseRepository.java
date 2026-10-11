package com.airtribe.learntrack.repository;
import com.airtribe.learntrack.entity.Course;
import java.util.ArrayList;

public class CourseRepository {
    private ArrayList<Course> courses = new ArrayList<>();

    //add courses to the course list
    public void addCourse(Course course) {
        courses.add(course);
    }

    //get all courses in the array
    public ArrayList<Course> getCourses() {
        return new ArrayList<>(courses);
    }

    //find a course by its ID
    public Course findCourseByID(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }
}
