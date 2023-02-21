package com.home.demo.demospringbootapp.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.home.demo.demospringbootapp.dto.StudentDto;
import com.home.demo.demospringbootapp.entities.Student;

@Mapper
public interface StudentMapper {
		
	StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

	StudentDto toStudentDto(Student student);
	
	@Mapping(source = "studentDto", target = "Person")
	@InheritInverseConfiguration
	Student toStudent(StudentDto studentDto);
	
}
