package com.home.demo.demospringbootapp.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.home.demo.demospringbootapp.dto.ProfessorDto;
import com.home.demo.demospringbootapp.entities.Professor;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {
	ProfessorMapper INSTANCE = Mappers.getMapper(ProfessorMapper.class);

	@Mapping(source = "teachingCourses", target = "numberOfTeachingCourses")
	@Mapping(source = "students", target = "listOfSupervisingStudents")//, qualifiedByName = "mapStudents")
	@Mapping(source = "courses", target = "listOfCourses")
	ProfessorDto toProfessorDto(Professor professor);
	
	@InheritInverseConfiguration
	Professor toProfessor(ProfessorDto professorDto);

//	@Named("mapStudents")
//	default List<StudentDto> mapStudents(List<Student> students) {
//		return StudentMapper.INSTANCE.toStudentDtoList(students);
//	}
	
}
