package com.home.demo.demospringbootapp.mappers;

import com.home.demo.demospringbootapp.enums.CourseStatus;
import jdk.jfr.Name;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.home.demo.demospringbootapp.dto.CourseDto;
import com.home.demo.demospringbootapp.entities.Course;
import com.home.demo.demospringbootapp.entities.Professor;

@Mapper(componentModel = "spring")
public interface CourseMapper {
	
	CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

	@Mapping(source = "professor.lastName", target = "professorName")
//	@Mapping(target = "status", qualifiedByName = "mapStatus")
	@Mapping(source = "professor.professorId", target = "professorId")
//				qualifiedByName = "setFullName")
	CourseDto toCourseDto(Course course);
//	
	@InheritInverseConfiguration
	Course toCourse(CourseDto courseDto);

//	@Named("mapStatus")
//	default CourseStatus mapStatus(Course course) {
//		return course.getStatus() == null ? CourseStatus.AVAILABLE : course.getStatus();
//	}
	
	//TODO waiting for mapstruct release 1.5.4 to fix this issue
	@Named("setFullName")
	default String setFullName(Professor professor) {
		return professor != null ? professor.getFullName() : null;
	}

}
