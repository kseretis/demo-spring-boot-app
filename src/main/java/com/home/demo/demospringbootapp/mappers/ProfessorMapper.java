package com.home.demo.demospringbootapp.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.home.demo.demospringbootapp.dto.ProfessorDto;
import com.home.demo.demospringbootapp.entities.Professor;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {

	@Mapping(source = "teachingCourses", target = "numberOfTeachingCourses")
	@Mapping(source = "students", target = "listOfSupervisingStudents")
	ProfessorDto toProfessorDto(Professor professor);
	
	@InheritInverseConfiguration
	Professor toProfessor(ProfessorDto professorDto);
	
}
