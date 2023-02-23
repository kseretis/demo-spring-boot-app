package com.home.demo.demospringbootapp.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.demo.demospringbootapp.mappers.StudentMapper;
import com.home.demo.demospringbootapp.repositories.StudentRepository;
import com.home.demo.demospringbootapp.dto.StudentDto;
import com.home.demo.demospringbootapp.entities.Student;

@Service
public class StudentService {
	
	Logger logger = LoggerFactory.getLogger(StudentService.class);
	
	@Autowired
	private StudentRepository studentRepository;
	
	public List<StudentDto> getAllStudents(){
		return mapAndUpdateStudentsDto(studentRepository.findAll());
	}
	
	public StudentDto getStudentById(String studentId) {
		StudentDto student = studentRepository.findById(UUID.fromString(studentId)).
										map(StudentMapper.INSTANCE::toStudentDto).get();
		try {
			student.updateSupervisorInfo(studentRepository.fetchSupervisor(student.getStudentId()));
		} catch (NullPointerException ex) {}
		return student;
	}
	
	public List<StudentDto> getStudentsByFirstName(String firstName) {
		return mapAndUpdateStudentsDto(studentRepository.findByFirstName(firstName));
	}
	
	public List<StudentDto> getStudentsByLastName(String lastName) {
		return mapAndUpdateStudentsDto(studentRepository.findByLastName(lastName));
	}
	
	public List<StudentDto> getStudentsByDateOfBirth(String dateOfBirth) {
		return mapAndUpdateStudentsDto(studentRepository.findByDateOfBirth(LocalDate.parse(dateOfBirth)));
	}
	
	public List<StudentDto> getStudentsByClassYear(String classYear) {
		return mapAndUpdateStudentsDto(studentRepository.findByClassYear(Integer.parseInt(classYear)));
	}
	
	public List<StudentDto> getStudentsByGrade(String grade) {
		return mapAndUpdateStudentsDto(studentRepository.findByGrade(Double.parseDouble(grade)));
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
