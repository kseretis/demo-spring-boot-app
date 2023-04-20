package com.home.demo.demospringbootapp.student;

import java.util.*;
import java.util.stream.Collectors;

import com.home.demo.demospringbootapp.specifications.GenericSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepository studentRepository;
	private final GenericSpecification<Student> studentSpecification;

	public List<StudentDto> getStudents(Map<String, String> params) {
		
		// Filter and find the students		
		List<Student> students = studentRepository.findAll(studentSpecification.specifyFilters(params)).orElseThrow();

		log.info("Students: {}", Arrays.toString(students.toArray()));
				
		// Map to DTO
		List<StudentDto> studentsDto = students.stream().map(StudentMapper.INSTANCE::toStudentDto).collect(Collectors.toList());
		
		// Fetch supervisor
		studentsDto.forEach( student -> {
			Optional<StudentDto> studentDto = studentRepository.fetchSupervisor(student.getStudentId());
			studentDto.ifPresent(student::updateSupervisorInfo);
		});
		log.info("Students (DTO): {}", Arrays.toString(studentsDto.toArray()));
		return studentsDto;
	}
	
	public StudentDto getStudent(UUID id) {
		Optional<Student> student = studentRepository.findById(id);
		return student.map(StudentMapper.INSTANCE::toStudentDto).orElseThrow();
	}

	@Transactional
	public void addStudent(@NotNull StudentDto studentDto) {
		if (studentDto.getSupervisorId() != null) {
			studentRepository.fetchProfessor(studentDto.getStudentId()).ifPresent(studentDto::updateSupervisorInfo);
		}
		log.info("Student supervisor matched: {}", studentDto);
		
		Student newStudent = StudentMapper.INSTANCE.toStudent(studentDto);
		studentRepository.save(newStudent);
		log.info("Student mapped & added: {}", newStudent);
	}

	@Transactional
	public void updateStudent(UUID id, @NotNull StudentDto studentDto) {
		studentRepository.findById(id).ifPresent(student -> studentDto.setStudentId(student.getStudentId()));
		log.info("Student (DTO) found: {}", studentDto);
		Student updatedStudent = StudentMapper.INSTANCE.toStudent(studentDto);
		studentRepository.save(updatedStudent);
		log.info("Student updated: {}", updatedStudent);
	}

}
