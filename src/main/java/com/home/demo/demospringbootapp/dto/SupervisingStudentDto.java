package com.home.demo.demospringbootapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(value = {"firstName", "lastName", "dateOfBirth", "supervisorId", "supervisorName"})
public class SupervisingStudentDto extends StudentDto{

    public SupervisingStudentDto(UUID studentId, String firstName, String lastName, int classYear, double grade) {
        super(studentId, firstName, lastName, classYear, grade);
    }

}
