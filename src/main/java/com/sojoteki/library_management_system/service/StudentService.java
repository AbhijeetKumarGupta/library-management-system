package com.sojoteki.library_management_system.service;

import com.sojoteki.library_management_system.model.Student;
import com.sojoteki.library_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String saveStudent(Student student){
        studentRepository.save(student);
        return "Student saved successfully";
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(int studentId){
        Optional<Student> student = studentRepository.findById(studentId);
        return student.orElse(null);
    }
}
