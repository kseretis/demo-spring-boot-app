package com.home.demo.demospringbootapp.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;
    private Student student1;
    private Student student2;

    @BeforeEach
    void setUp() {
        student1 = Student.builder()
                .firstName("John")
                .lastName("Wick")
                .dateOfBirth(LocalDate.now())
                .classYear(3)
                .grade(5.2)
                .build();

        student2 = Student.builder()
                .firstName("Alisa")
                .lastName("Moore")
                .dateOfBirth(LocalDate.now())
                .classYear(2)
                .grade(6.2)
                .build();

        studentRepository.save(student1);
        studentRepository.save(student2);
    }

    @Test
    void findAll() {
        List<Student> all = studentRepository.findAll();
        assertEquals(2, all.size());
    }

    @Test
    @Disabled
    void fetchSupervisor() {
        //TODO
    }

    @Test
    @Disabled
    void fetchProfessor() {
        //TODO
    }

}