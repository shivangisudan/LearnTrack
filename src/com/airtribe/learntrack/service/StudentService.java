package com.airtribe.learntrack.service;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IDGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;

//uses repository to find a student and decide what happens if ID doesn't exist
public class StudentService {
    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //add a student
    public Student addStudent(String firstName, String lastName, String email, String batch) {
        //validators
        InputValidator.requireNonBlank(firstName, "First name");
        InputValidator.requireNonBlank(lastName, "Last name");
        InputValidator.requireNonBlank(batch, "Batch");
        InputValidator.requireValidEmail(email);


        Student student = new Student(
                IDGenerator.getNextStudentId(), firstName, lastName, email, batch
        );
        studentRepository.addStudent(student);
        return student;
    }

    // update students
    public void updateStudent(int id, String firstName, String lastName, String email, String batch) {
        //validators
        InputValidator.requireNonBlank(firstName, "First name");
        InputValidator.requireNonBlank(lastName, "Last name");
        InputValidator.requireNonBlank(batch, "Batch");
        InputValidator.requireValidEmail(email);


        Student student = getStudentById(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setBatch(batch);
    }

    //list all students
    public ArrayList<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    //find student by ID & error handling
    public Student getStudentById(int id) {
        Student student = studentRepository.findStudentByID(id);

        if (student == null) {
            throw new  EntityNotFoundException("Student with ID " + id + " not found");
        }
        return student;
    }

    //deactivate a student
    public void deactivateStudent(int id) {
        Student student = getStudentById(id);
        student.setActive(false);
    }
}
