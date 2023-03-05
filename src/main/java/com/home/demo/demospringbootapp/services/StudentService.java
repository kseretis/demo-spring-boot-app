package com.home.demo.demospringbootapp.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.home.demo.demospringbootapp.specifications.GenericSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.home.demo.demospringbootapp.mappers.StudentMapper;
import com.home.demo.demospringbootapp.repositories.StudentRepository;
import com.home.demo.demospringbootapp.specifications.StudentSpecifications;
import lombok.extern.slf4j.Slf4j;
import com.home.demo.demospringbootapp.dto.StudentDto;
import com.home.demo.demospringbootapp.entities.Student;

@Slf4j
@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private GenericSpecification<Student> genericSpecification;
	
	public List<StudentDto> getStudents(Map<String, String> params) {
		
		// Filter and find the students		
		List<Student> students = studentRepository.findAll(specifyFilters(params));
		log.info("Students: {}", Arrays.toString(students.toArray()));
				
		// Map to DTO
		List<StudentDto> studentsDto = students.stream().map(StudentMapper.INSTANCE::toStudentDto).collect(Collectors.toList());
		
		// Fetch supervisor
		studentsDto.forEach( student -> {
			try {
				student.updateSupervisorInfo(studentRepository.fetchSupervisor(student.getStudentId()));
			} catch (NullPointerException ex) {}
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
	
	private Specification<Student> specifyFilters(Map<String, String> params){
		Specification<Student> spec = Specification.where(null);
		Specification<Student> newspec = Specification.where(null);

		if (params.get("firstName") != null) {
			spec = spec.and(StudentSpecifications.nameLike("firstName", params.get("firstName")));
			newspec = spec.and(genericSpecification.nameLike("firstName", params.get("firstName")));
		}
		if (params.get("lastName") != null) 
			spec = spec.and(StudentSpecifications.nameLike("lastName", params.get("lastName")) );
		
		if (params.get("dateOfBirth") != null) 
			spec = spec.and(StudentSpecifications.withProperty("dateOfBirth", LocalDate.parse(params.get("dateOfBirth"))));
		
		if (params.get("classYear") != null) 
			spec = spec.and(StudentSpecifications.withProperty("classYear", Integer.parseInt(params.get("classYear"))));
		
		if (params.get("grade") != null) 
			spec = spec.and(StudentSpecifications.withProperty("grade", Double.parseDouble(params.get("grade"))));

		return newspec;
	}
}
