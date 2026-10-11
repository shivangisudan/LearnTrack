package com.airtribe.learntrack.repository;
import com.airtribe.learntrack.entity.Student;
import java.util.ArrayList;

public class StudentRepository {
    private ArrayList<Student> students = new ArrayList<>();

    //add students to the array
    public void addStudent(Student student) {
        students.add(student);
    }

    //get ALL students in the array
    public ArrayList<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    //Find a Student by their ID
    public Student findStudentByID(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
}
