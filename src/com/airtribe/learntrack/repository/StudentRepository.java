package com.airtribe.learntrack.repository;
import com.airtribe.learntrack.entity.Student;
import java.util.ArrayList;

public class StudentRepository {
    private ArrayList<Student> students = new ArrayList<>();

    //add students to the list
    public void addStudent(Student student) {
        students.add(student);
    }

    //get all students in the list
    public ArrayList<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    //find a student by ID
    public Student findStudentByID(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
}
