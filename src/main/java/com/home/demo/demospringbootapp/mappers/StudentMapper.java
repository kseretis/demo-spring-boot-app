package com.home.demo.demospringbootapp.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import com.home.demo.demospringbootapp.entities.Professor;
import com.home.demo.demospringbootapp.entities.Student;
import com.home.demo.demospringbootapp.dto.StudentDto;

@Mapper
public interface StudentMapper {
		
	StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
//	
//	@Mapping(source = "studentId", target = "studentId")
//	@Mapping(source = "firstName", target = "firstName")
//	@Mapping(source = "lastName", target = "lastName")
//	@Mapping(source = "dateOfBirth", target = "dateOfBirth")
//	@Mapping(source = "classYear", target = "classYear")
//	@Mapping(source = "grade", target = "grade")
//	StudentDto studentToStudentDto(Student student);
//	
	StudentDto toStudentDto(Student student);
	
	Student toStudent(StudentDto studentDto);
	
	StudentDto updateStudentDto(StudentDto studentDto);
	
//	@Mapping(source = "professor.professorId", target = "supervisorId")
//	@Mapping(source = "professor.firstName", target = "supervisorFirstName")
//	@Mapping(source = "professor.lastName", target = "supervisorLastName")
//	StudentDto studentTest(Student student, Professor professor);
////	Student studentDtoToStudent(StudentDto studentDto);

}
