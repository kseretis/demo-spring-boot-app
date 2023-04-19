package com.home.demo.demospringbootapp.student;

import com.home.demo.demospringbootapp.specifications.GenericSpecification;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private GenericSpecification<Student> studentSpecification;
    private AutoCloseable autoCloseable;
    private StudentService studentService;
    private final Map<String, String> emptyMap = new HashMap<>();

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        studentService = new StudentService(studentRepository, studentSpecification);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    @Disabled
    void getStudents() {
        studentService.getStudents(emptyMap);
        verify(studentRepository).findAll();
    }

    @Test
    void getStudent() {
        UUID randomUUID = UUID.randomUUID();
        studentService.getStudent(randomUUID);
        verify(studentRepository).findById(randomUUID);
    }

    @Test
    void addStudent() {
    }

    @Test
    void updateStudent() {
    }
}