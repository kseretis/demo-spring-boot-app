package com.home.demo.demospringbootapp.dto.projections;

import java.util.UUID;

public interface SupervisingStudentProjection {
    UUID getStudentId();
    String getFullName();
    int getClassYear();
    double getGrade();

}
