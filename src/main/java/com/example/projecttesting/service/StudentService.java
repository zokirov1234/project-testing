package com.example.projecttesting.service;

import com.example.projecttesting.exp.EmailFoundException;
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

    public boolean save(StudentEntity student) {
        Optional<StudentEntity> existsEmail =
                studentRepository.findByEmail(student.getEmail());
        if (existsEmail.isPresent()) {
            throw new EmailFoundException(
                    "Email " + student.getEmail() + " taken"
            );
        }

        studentRepository.save(student);
        return true;
    }

    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }
}
