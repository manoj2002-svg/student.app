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

    
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

   
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) { 
        Optional<Student> student = studentRepository.findById(id); 
        return student.orElse(null);
    }

    
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) { 
        Student student = studentRepository.findById(id).orElseThrow();
        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setCourse(studentDetails.getCourse());
        return studentRepository.save(student);
    }

   
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) { 
        studentRepository.deleteById(id);
        return "Student deleted successfully!";
    }
}

