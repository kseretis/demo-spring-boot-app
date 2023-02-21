package com.home.demo.demospringbootapp.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.home.demo.demospringbootapp.entities.Student;
import com.home.demo.demospringbootapp.dto.StudentDto;

@Mapper
public interface StudentMapper {
		
	StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

	StudentDto toStudentDto(Student student);
	
	Student toStudent(StudentDto studentDto);
	
}
