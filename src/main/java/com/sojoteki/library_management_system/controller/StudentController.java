package com.sojoteki.library_management_system.controller;

import com.sojoteki.library_management_system.model.Student;
import com.sojoteki.library_management_system.request_dto.StudentRequestDto;
import com.sojoteki.library_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<?> saveStudent(@RequestBody StudentRequestDto studentRequestDto){
        try {
            String response = studentService.saveStudent(studentRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getClass()+":\n"+"Save operation failed - "+e.getMessage());
        }
    }

    @GetMapping("")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id){
        try {
            Student student = studentService.getStudentById(id);
            return ResponseEntity.status(HttpStatus.OK).body(student);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getClass()+":\n"+"Get operation failed - "+e.getMessage());
        }
    }
}
