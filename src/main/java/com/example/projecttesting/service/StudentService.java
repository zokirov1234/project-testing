package com.example.projecttesting.service;

import com.example.projecttesting.exp.EmailFoundException;
import com.example.projecttesting.model.dto.StudentRequest;
import com.example.projecttesting.model.entity.StudentEntity;
import com.example.projecttesting.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public boolean save(StudentRequest studentRequest) {
        Optional<StudentEntity> existsEmail =
                studentRepository.findByEmail(studentRequest.email());
        if (existsEmail.isPresent()) {
            throw new EmailFoundException(
                    "Email " + studentRequest.email() + " taken"
            );
        }
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setEmail(studentRequest.email());
        studentEntity.setName(studentRequest.name());
        studentEntity.setPassword(studentRequest.password());
        studentRepository.save(studentEntity);
        return true;
    }

    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }
}
