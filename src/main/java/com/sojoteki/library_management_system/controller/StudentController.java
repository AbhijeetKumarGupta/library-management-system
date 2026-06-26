package com.sojoteki.library_management_system.controller;

import com.sojoteki.library_management_system.model.Student;
import com.sojoteki.library_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public String saveStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }

    @GetMapping("")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("{id}")
    public Student getStudentById(@PathVariable int id){
        return studentService.getStudentById(id);
    }
}
