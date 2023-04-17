package com.home.demo.demospringbootapp.student;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.home.demo.demospringbootapp.professor.Professor;

@Mapper(componentModel = "spring")
public interface StudentMapper {

	StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
	
	@Mapping(source = "supervisor.professorId", target = "supervisorId")
	@Mapping(source = "supervisor", target = "supervisorName", qualifiedByName = "setFullName")
	StudentDto toStudentDto(Student student);
	
	@InheritInverseConfiguration
	Student toStudent(StudentDto studentDto);
	
	@Mapping(source = "supervisor.professorId", target = "supervisorId")
	@Mapping(source = "supervisor", target = "supervisorName", qualifiedByName = "setFullName")
	List<StudentDto> toStudentDtoList(List<Student> students);
	
	@InheritInverseConfiguration
	List<Student> toStudentList(List<StudentDto> students);
	
	@Named("setFullName")
	default String setFullName(Professor professor) {
		return professor != null ? professor.getFullName() : null;
	}
	
}
