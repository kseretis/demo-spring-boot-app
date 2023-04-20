package com.home.demo.demospringbootapp.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        List<Student> mockStudents = StudentMocks.mockStudents;

        studentRepository.save(mockStudents.get(0));
        studentRepository.save(mockStudents.get(1));
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