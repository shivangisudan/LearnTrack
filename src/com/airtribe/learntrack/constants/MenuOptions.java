package com.airtribe.learntrack.constants;

//menu option numbers
public final class MenuOptions {
    public static final int BACK_OR_EXIT = 0;
    public static final int STUDENTS = 1;
    public static final int COURSES = 2;
    public static final int ENROLLMENTS = 3;
    //students
    public static final int ADD_STUDENT = 1;
    public static final int LIST_STUDENTS = 2;
    public static final int FIND_STUDENT = 3;
    public static final int UPDATE_STUDENT = 4;
    public static final int DEACTIVATE_STUDENT = 5;
    //courses
    public static final int ADD_COURSE = 1;
    public static final int LIST_COURSES = 2;
    public static final int FIND_COURSE = 3;
    public static final int ACTIVATE_COURSE = 4;
    public static final int DEACTIVATE_COURSE = 5;
    //enrollments
    public static final int ENROLL_STUDENT = 1;
    public static final int LIST_STUDENT_ENROLLMENTS = 2;
    public static final int FIND_ENROLLMENT = 3;
    public static final int COMPLETE_ENROLLMENT = 4;
    public static final int CANCEL_ENROLLMENT = 5;

    private MenuOptions() {
    }
}
