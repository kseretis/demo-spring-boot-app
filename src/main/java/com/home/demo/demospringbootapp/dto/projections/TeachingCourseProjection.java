package com.home.demo.demospringbootapp.dto.projections;

import com.home.demo.demospringbootapp.enums.CourseStatus;
import java.util.UUID;

public interface TeachingCourseProjection {
    UUID getCourseId();
    String getCourseName();
    CourseStatus getStatus();

}
