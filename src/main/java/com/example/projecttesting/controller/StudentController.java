package com.example.projecttesting.controller;


import com.example.projecttesting.model.entity.StudentEntity;
import com.example.projecttesting.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/add")
    public boolean addStudent(
            @RequestBody StudentEntity student
    ) {
        return studentService.save(student);
    }

    @GetMapping("/getAll")
    public List<StudentEntity> getAll() {
        return studentService.getAllStudents();
    }


}
