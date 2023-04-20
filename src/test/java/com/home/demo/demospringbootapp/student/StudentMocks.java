package com.home.demo.demospringbootapp.student;

import java.time.LocalDate;
import java.util.List;

public interface StudentMocks {

    List<Student> mockStudents = List.of(
            Student.builder()
                    .firstName("John")
                    .lastName("Wick")
                    .dateOfBirth(LocalDate.now())
                    .classYear(3)
                    .grade(5.2)
                    .build(),
            Student.builder()
                    .firstName("Alisa")
                    .lastName("Moore")
                    .dateOfBirth(LocalDate.now())
                    .classYear(2)
                    .grade(6.2)
                    .build()
    );

}
