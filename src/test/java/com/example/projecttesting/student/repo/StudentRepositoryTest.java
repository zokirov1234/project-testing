package com.example.projecttesting.student.repo;

import com.example.projecttesting.model.entity.StudentEntity;
import com.example.projecttesting.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @Test
    void existsEmail() {
        String email = "brain@gmail.com";
        StudentEntity student = new StudentEntity(
                4,"brain",email,"123456"
        );
        underTest.save(student);
        boolean exists = underTest.findByEmail(email).isPresent();
        assertThat(exists).isTrue();
    }
}