package com.home.demo.demospringbootapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.home.demo.demospringbootapp.enums.CourseStatus;

import java.util.UUID;

@JsonIgnoreProperties(value = {"coveredSeats", "maxSeats", "professorName", "professorId"})
public class TeachingCourseDto extends CourseDto {

    public TeachingCourseDto(UUID courseId, String courseName, CourseStatus status) {
        super(courseId, courseName, status);
    }

}
