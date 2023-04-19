package com.home.demo.demospringbootapp.course;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.home.demo.demospringbootapp.professor.Professor;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

	CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

	@Mapping(source = "professor", target = "professorName", qualifiedByName = "setFullName")
	@Mapping(source = "professor.professorId", target = "professorId")
	CourseDto toCourseDto(Course course);

	@InheritInverseConfiguration
	Course toCourse(CourseDto courseDto);

	@Mapping(source = "professor", target = "professorName", qualifiedByName = "setFullName")
	@Mapping(source = "professor.professorId", target = "professorId")
	List<CourseDto> toCourseDtoList(List<Course> courses);

	@InheritInverseConfiguration
	List<Course> toCourseList(List<CourseDto> coursesDto);

	@Named("setFullName")
	default String setFullName(Professor professor) {
		return professor != null ? professor.getFullName() : null;
	}

}
