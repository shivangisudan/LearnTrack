package com.airtribe.learntrack.entity;

public class Course {
    private int id;
    private String courseName;
    private String courseDescription;
    private int courseDuration;
    private boolean active;

    //constructor
    public Course(int id, String courseName, String courseDescription, int courseDuration) {
        this.id = id;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseDuration = courseDuration;

        this.active = true;
    }
    //getters
    public int getId() {
        return id;
    }
    public String getCourseName() {
        return courseName;
    }
    public String getCourseDescription() {
        return courseDescription;
    }
    public int getCourseDuration() {
        return courseDuration;
    }
    public boolean isActive() {
        return active;
    }

    //setters
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
    public void setCourseDuration(int courseDuration) {
        this.courseDuration = courseDuration;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public String getCourseDetails(){
        return id + " " + courseName + " " + courseDescription + " " + courseDuration;
    }
}