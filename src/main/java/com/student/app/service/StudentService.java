package com.student.app.service;

import com.student.app.model.Student;

import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);
    List<Student> getAllStudents();
    Student getStudentById(Long id);
}
