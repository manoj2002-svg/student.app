package com.student.app.service;

import com.student.app.model.Student;
import com.student.app.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public Student createStudent(Student student) {
        return repository.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Optional<Student> optionalStudent = repository.findById(id);
        if (optionalStudent.isPresent()) {
            Student existing = optionalStudent.get();
            existing.setName(student.getName());
            existing.setEmail(student.getEmail());
            existing.setCourse(student.getCourse());
            return repository.save(existing);
        } else {
            throw new RuntimeException("Student not found with id " + id);
        }
    }

    @Override
    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id " + id));
    }
}
