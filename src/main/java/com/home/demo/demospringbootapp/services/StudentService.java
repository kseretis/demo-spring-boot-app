package com.home.demo.demospringbootapp.services;

import java.util.*;
import java.util.stream.Collectors;

import com.home.demo.demospringbootapp.specifications.GenericSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.home.demo.demospringbootapp.mappers.StudentMapper;
import com.home.demo.demospringbootapp.repositories.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import com.home.demo.demospringbootapp.dto.StudentDto;
import com.home.demo.demospringbootapp.entities.Student;

@Slf4j
@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private GenericSpecification<Student> studentSpecification;

	public List<StudentDto> getStudents(Map<String, String> params) {
		
		// Filter and find the students		
		List<Student> students = studentRepository.findAll(studentSpecification.specifyFilters(params));
		log.info("Students: {}", Arrays.toString(students.toArray()));
				
		// Map to DTO
		List<StudentDto> studentsDto = students.stream().map(StudentMapper.INSTANCE::toStudentDto).collect(Collectors.toList());
		
		// Fetch supervisor
		studentsDto.forEach( student -> {
			try {
				student.updateSupervisorInfo(studentRepository.fetchSupervisor(student.getStudentId()));
			} catch (NullPointerException ignored) {}
		});
		log.info("Students (DTO): {}", Arrays.toString(studentsDto.toArray()));
		
		return studentsDto;
	}
	
	public StudentDto getStudent(UUID id) {
		return StudentMapper.INSTANCE.toStudentDto(studentRepository.findById(id).get());
	}
	
	public void addStudent(StudentDto studentDto) {
		if (studentDto.getSupervisorId() != null) {
			studentDto.updateSupervisorInfo(studentRepository
					.fetchProfessor(studentDto.getStudentId()));
		}
		log.info("Student supervisor matched: {}", studentDto.toString());
		
		Student newStudent = StudentMapper.INSTANCE.toStudent(studentDto);
		studentRepository.save(newStudent);
		log.info("Student mapped & added: {}", newStudent.toString());
	}
	
	public void updateStudent(UUID id, StudentDto studentDto) {
		studentDto.setStudentId( studentRepository.findById(id).get().getStudentId());
		log.info("Student (DTO) found: {}", studentDto.toString());
		Student updatedStudent = StudentMapper.INSTANCE.toStudent(studentDto);
		studentRepository.save(updatedStudent);
		log.info("Student updated: {}", updatedStudent.toString());
	}

}
