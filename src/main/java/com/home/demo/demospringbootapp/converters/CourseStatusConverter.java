package com.home.demo.demospringbootapp.converters;

import com.home.demo.demospringbootapp.enums.CourseStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CourseStatusConverter implements AttributeConverter<CourseStatus, String> {

    @Override
    public String convertToDatabaseColumn(CourseStatus attribute) {
        return attribute.name().toLowerCase();
    }

    @Override
    public CourseStatus convertToEntityAttribute(String dbData) {
        return CourseStatus.valueOf(dbData.toUpperCase());
    }
}

