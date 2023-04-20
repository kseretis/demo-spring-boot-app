package com.home.demo.demospringbootapp.student;

import com.home.demo.demospringbootapp.specifications.GenericSpecification;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private GenericSpecification<Student> studentSpecification;
    private AutoCloseable autoCloseable;
    @InjectMocks
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
    void willThrowNoSuchElementException() {
        assertThatThrownBy(() -> studentService.getStudents(emptyMap)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void shouldGetAllStudents() {
        // Expected value
        final Optional<List<Student>> expectedStudents = Optional.of(StudentMocks.mockStudents);
        when(studentRepository.findAll((Specification<Student>) null)).thenReturn(expectedStudents);

        final List<StudentDto> expectedStudentsDto = StudentMapper.INSTANCE.toStudentDtoList(expectedStudents.get());

        // Actual value
        final List<StudentDto> actualStudents = studentService.getStudents(emptyMap);

        // Assert
        assertEquals(expectedStudentsDto, actualStudents);
        verify(studentRepository, times(1)).findAll((Specification<Student>) null);
    }

    @Test
    void shouldGetAStudent() {
        // Expected value, interact with repository
        final Student expectedStudent = StudentMocks.mockStudents.get(0);
        final UUID randomUUID = UUID.randomUUID();
        when(studentRepository.findById(randomUUID)).thenReturn(Optional.of(expectedStudent));

        final StudentDto expectedStudentDto = StudentMapper.INSTANCE.toStudentDto(expectedStudent);

        // Actual value
        final var actual = studentService.getStudent(randomUUID);

        // Assert
        assertEquals(actual, expectedStudentDto);
        verify(studentRepository, times(1)).findById(randomUUID);
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    void shouldAddAStudent() {
        // Expected value
        final StudentDto expectedStudentDto = StudentMapper.INSTANCE.toStudentDto(StudentMocks.mockStudents.get(1));
        final Student expectedStudent = StudentMapper.INSTANCE.toStudent(expectedStudentDto);
        when(studentRepository.save(any(Student.class))).thenReturn(expectedStudent);

        // Actual
        studentService.addStudent(expectedStudentDto);

        // Assert
        verify(studentRepository, times(1)).save(expectedStudent);
    }

    @Test
    void shouldUpdateAStudent() {
        // Expected value
        final UUID randomUUID = UUID.randomUUID();
        final StudentDto expectedStudentDto = StudentMapper.INSTANCE.toStudentDto(StudentMocks.mockStudents.get(1));
        final Student expectedStudent = StudentMapper.INSTANCE.toStudent(expectedStudentDto);
        when(studentRepository.save(any(Student.class))).thenReturn(expectedStudent);

        // Actual
        studentService.updateStudent(randomUUID, expectedStudentDto);

        // Assert
        verify(studentRepository).save(expectedStudent);
    }

}