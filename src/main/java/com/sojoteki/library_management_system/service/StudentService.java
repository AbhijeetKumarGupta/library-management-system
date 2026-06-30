package com.sojoteki.library_management_system.service;

import com.sojoteki.library_management_system.model.Card;
import com.sojoteki.library_management_system.model.Student;
import com.sojoteki.library_management_system.repository.CardRepository;
import com.sojoteki.library_management_system.repository.StudentRepository;
import com.sojoteki.library_management_system.request_dto.StudentRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CardRepository cardRepository;

    public String saveStudent(StudentRequestDto studentRequestDto){
        Card card = cardRepository.findById(studentRequestDto.getCardId()).orElse(null);

        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setEmail(studentRequestDto.getEmail());
        student.setMobile(studentRequestDto.getMobile());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setSemester(studentRequestDto.getSemester());
        student.setGender(studentRequestDto.getGender());
        student.setAddress(studentRequestDto.getAddress());
        student.setDob(studentRequestDto.getDob());
        if(card != null) card.setStudent(student);
        student.setCard(card);

        studentRepository.save(student);

        return "Student saved successfully";
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(int studentId){
        Optional<Student> student = studentRepository.findById(studentId);

        if(student.isPresent()){
            return student.get();
        }else{
            throw new RuntimeException("Student with id " + studentId + " not found");
        }
    }
}
