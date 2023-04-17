package com.home.demo.demospringbootapp.converters;

import com.home.demo.demospringbootapp.course.CourseStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.jetbrains.annotations.NotNull;

@Converter(autoApply = true)
public class CourseStatusConverter implements AttributeConverter<CourseStatus, String> {

    @Override
    public String convertToDatabaseColumn(@NotNull CourseStatus attribute) {
        return attribute.name();
    }

    @Override
    public CourseStatus convertToEntityAttribute(@NotNull String dbData) {
        return CourseStatus.valueOf(dbData.toUpperCase());
    }

}

