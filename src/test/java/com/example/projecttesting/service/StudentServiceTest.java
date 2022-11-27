package com.example.projecttesting.service;

import com.example.projecttesting.exp.EmailFoundException;
import com.example.projecttesting.model.dto.StudentRequest;
import com.example.projecttesting.model.entity.StudentEntity;
import com.example.projecttesting.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
    }


    @Test
    void getAllStudents() {
        underTest.getAllStudents();
        verify(studentRepository).findAll();
    }

    @Test
    void save() {
        //given
        StudentEntity student = new StudentEntity();
        //"george","george@gmail.com","1234567"
        student.setEmail("george@gmail");
        student.setName("george");
        student.setPassword("1234567");
        //when
        underTest.save(student);
        //then
        ArgumentCaptor<StudentEntity> studentRequestArgumentCaptor =
                ArgumentCaptor.forClass(StudentEntity.class);
        verify(studentRepository)
                .save(studentRequestArgumentCaptor.capture());

        StudentEntity captorValue = studentRequestArgumentCaptor.getValue();

        assertThat(captorValue).isEqualTo(student);

    }

    @Test
    void isThrownException(){
        StudentEntity student = new StudentEntity();
        student.setEmail("george@gmail");
        student.setName("george");
        student.setPassword("1234567");

        Optional<StudentEntity> expectedStudent = Optional.of(student);

        given(studentRepository.findByEmail(anyString()))
                .willReturn(expectedStudent);

        assertThatThrownBy(() -> underTest.save(student))
                .isInstanceOf(EmailFoundException.class)
                .hasMessageContaining("Email "+ student.getEmail() + " taken");
    }

}