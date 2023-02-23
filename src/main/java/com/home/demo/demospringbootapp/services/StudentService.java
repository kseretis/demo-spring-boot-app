package com.home.demo.demospringbootapp.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.home.demo.demospringbootapp.mappers.StudentMapper;
import com.home.demo.demospringbootapp.repositories.StudentRepository;
import com.home.demo.demospringbootapp.specifications.StudentSpecifications;
import com.home.demo.demospringbootapp.dto.StudentDto;
import com.home.demo.demospringbootapp.entities.Student;

@Service
public class StudentService {
	
	Logger logger = LoggerFactory.getLogger(StudentService.class);
	
	@Autowired
	private StudentRepository studentRepository;
	
	public List<StudentDto> getStudents(Map<String, String> params) {
		Specification<Student> spec = Specification.where(null);
		
//		if (params.get("studentId") != null) 
//			spec = spec.and(StudentSpecifications.withProperty("studentId", UUID.fromString(params.get("studentId"))) );
		
		if (params.get("firstName") != null) 
			spec = spec.and(StudentSpecifications.nameLike("firstName", params.get("firstName")));
		
		if (params.get("lastName") != null) 
			spec = spec.and(StudentSpecifications.nameLike("lastName", params.get("lastName")) );
		
		if (params.get("dateOfBirth") != null) 
			spec = spec.and(StudentSpecifications.withProperty("dateOfBirth", LocalDate.parse(params.get("dateOfBirth"))));
		
		if (params.get("classYear") != null) 
			spec = spec.and(StudentSpecifications.withProperty("classYear", Integer.parseInt(params.get("classYear"))));
		
		if (params.get("grade") != null) 
			spec = spec.and(StudentSpecifications.withProperty("grade", Double.parseDouble(params.get("grade"))));
				
		return mapAndUpdateStudentsDto(studentRepository.findAll(spec));	
	}
	
	public StudentDto getStudent(UUID id) {
		return StudentMapper.INSTANCE.toStudentDto(studentRepository.findById(id).get());
	}
	
	public void addStudent(StudentDto studentDto) {
		if (studentDto.getSupervisorId() != null) {
			studentDto.updateSupervisorInfo(studentRepository
					.fetchProfessor(studentDto.getStudentId()));
		}
		logger.info("Student supervisor matched: {}", studentDto.toString());
		
		Student newStudent = StudentMapper.INSTANCE.toStudent(studentDto);
		logger.info("Student mapped: {}", newStudent.toString());
		
		studentRepository.save(newStudent);
		logger.info("Student added: {}", newStudent.toString());
	}
	
	public void updateStudent(UUID id, StudentDto studentDto) {
		studentDto.setStudentId(studentRepository.findById(id).get().getStudentId());
		logger.info("Student (DTO) found: {}", studentDto.toString());
		Student updatedStudent = StudentMapper.INSTANCE.toStudent(studentDto);
		studentRepository.save(updatedStudent);
		logger.info("Student updated {}", updatedStudent.toString());
	}
	
	/*
	 * 1.	Map the Student list to StudentDto list
	 * 2.	Fetch the supervisor info for each of the StudentDto list entries
	 * 3.	Update StudentDto list and return
	 *
	 */
	public List<StudentDto> mapAndUpdateStudentsDto(List<Student> students){
		logger.info("Before mapping: {}", Arrays.toString(students.toArray()));
		List<StudentDto> studentsDto = students.stream().map(StudentMapper.INSTANCE::toStudentDto).collect(Collectors.toList());

		studentsDto.forEach( student -> {
			try {
				student.updateSupervisorInfo(studentRepository.fetchSupervisor(student.getStudentId()));
			} catch (NullPointerException ex) {}
		});
		logger.info("After fetch & map: {}", Arrays.toString(studentsDto.toArray()));
		return studentsDto;
	}
	
}
