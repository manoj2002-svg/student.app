package com.student.app.controller;

import com.student.app.model.Student;
import com.student.app.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // GET all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // GET student by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) { // Change Integer to Long
        Optional<Student> student = studentRepository.findById(id); // Use Long for id
        return student.orElse(null);
    }

    // POST: Create new student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // PUT: Update student
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) { // Change Integer to Long
        Student student = studentRepository.findById(id).orElseThrow();
        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setCourse(studentDetails.getCourse());
        return studentRepository.save(student);
    }

    // DELETE: Delete student
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) { // Change Integer to Long
        studentRepository.deleteById(id);
        return "Student deleted successfully!";
    }
}

