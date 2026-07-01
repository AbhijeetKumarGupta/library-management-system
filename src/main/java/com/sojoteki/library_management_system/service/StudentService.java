package com.sojoteki.library_management_system.service;

import com.sojoteki.library_management_system.exception.BadRequestException;
import com.sojoteki.library_management_system.exception.ResourceNotFoundException;
import com.sojoteki.library_management_system.model.Card;
import com.sojoteki.library_management_system.model.Student;
import com.sojoteki.library_management_system.repository.CardRepository;
import com.sojoteki.library_management_system.repository.StudentRepository;
import com.sojoteki.library_management_system.request_dto.StudentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CardRepository cardRepository;

    public String saveStudent(StudentRequestDto studentRequestDto) {
        Card card = getUnassignedCard(studentRequestDto.getCardId(), true);

        Student student = new Student();
        applyRequest(student, studentRequestDto);
        card.setStudent(student);
        student.setCard(card);
        studentRepository.save(student);

        return "Student saved successfully";
    }

    public Page<Student> getAllStudents(String sortBy, String sortOrder, int pageNo, int pageSize) {
        if (pageNo < 0) {
            throw new BadRequestException("Page number cannot be negative");
        }
        if (pageSize < 1) {
            throw new BadRequestException("Page size must be at least 1");
        }

        return studentRepository.findAll(
                PageRequest.of(pageNo, pageSize,
                        sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
                )
        );
    }

    public Student getStudentById(int studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " + studentId + " not found"));
    }

    public Student getStudentByEmail(String email){
        Optional<Student> student = studentRepository.getStudentByEmail(email);

        if(student.isPresent()){
            return student.get();
        }else{
            throw new RuntimeException("Student with email " + email + " not found");
        }
    }

    public String updateStudent(int stdId, StudentRequestDto studentRequestDto) {
        Student student = getStudentById(stdId);
        Card card = getUnassignedCard(studentRequestDto.getCardId(), false);

        if (student.getCard() != null && student.getCard().getId() != card.getId()) {
            student.getCard().setStudent(null);
        }

        applyRequest(student, studentRequestDto);
        card.setStudent(student);
        student.setCard(card);
        studentRepository.save(student);

        return "Student updated successfully";
    }

    public String deleteStudent(int stdId) {
        Student student = getStudentById(stdId);
        studentRepository.delete(student);
        return "Student deleted successfully";
    }

    private Card getUnassignedCard(int cardId, boolean isAdd) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("Card with id " + cardId + " not found"));

        if (card.getStudent() != null && (isAdd || card.getStudent().getId() != cardId)) {
            throw new BadRequestException("Card with id " + cardId + " is already assigned");
        }

        return card;
    }

    private void applyRequest(Student student, StudentRequestDto studentRequestDto) {
        student.setName(studentRequestDto.getName());
        student.setEmail(studentRequestDto.getEmail());
        student.setMobile(studentRequestDto.getMobile());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setSemester(studentRequestDto.getSemester());
        student.setGender(studentRequestDto.getGender());
        student.setAddress(studentRequestDto.getAddress());
        student.setDob(studentRequestDto.getDob());
    }
}
