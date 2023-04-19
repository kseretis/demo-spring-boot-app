package com.home.demo.demospringbootapp.student;

import java.util.*;
import java.util.stream.Collectors;

import com.home.demo.demospringbootapp.specifications.GenericSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import javax.swing.text.html.Option;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepository studentRepository;
	private final GenericSpecification<Student> studentSpecification;

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
		Optional<Student> student = studentRepository.findById(id);
		return student.map(StudentMapper.INSTANCE::toStudentDto).orElseThrow();
	}

	@Transactional
	public void addStudent(@NotNull StudentDto studentDto) {
		if (studentDto.getSupervisorId() != null) {
			studentDto.updateSupervisorInfo(studentRepository
					.fetchProfessor(studentDto.getStudentId()));
		}
		log.info("Student supervisor matched: {}", studentDto.toString());
		
		Student newStudent = StudentMapper.INSTANCE.toStudent(studentDto);
		studentRepository.save(newStudent);
		log.info("Student mapped & added: {}", newStudent.toString());
	}

	@Transactional
	public void updateStudent(UUID id, @NotNull StudentDto studentDto) {
		studentDto.setStudentId( studentRepository.findById(id).get().getStudentId());
		log.info("Student (DTO) found: {}", studentDto.toString());
		Student updatedStudent = StudentMapper.INSTANCE.toStudent(studentDto);
		studentRepository.save(updatedStudent);
		log.info("Student updated: {}", updatedStudent.toString());
	}

}
