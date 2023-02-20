package com.home.demo.demospringbootapp.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.mapstruct.AfterMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.demo.demospringbootapp.entities.Student;
import com.home.demo.demospringbootapp.mappers.StudentMapper;
import com.home.demo.demospringbootapp.dto.StudentDto;
import com.home.demo.demospringbootapp.dto.repositories.StudentDtoRepository;
import com.home.demo.demospringbootapp.repositories.StudentRepository;

@Service
public class StudentService {
	
	private Logger logger = LoggerFactory.getLogger(StudentService.class);
	
	@Autowired
	private StudentRepository studentRepository;
	
//	@Autowired
//	private StudentDtoRepository studentDtoRepository;
	
//	public List<Student> getAllStudents(){
//		List<Student> students = new ArrayList<>();
//		studentRepository.findAll().forEach(students::add);
//		return students;
//	}
//	
//	public void addStudent(Student student) {
//		studentRepository.save(student);
//	}
//	
//	public void updateStudent(UUID studentId, Student updatedStudent) {
//		Optional<Student> student = studentRepository.findById(studentId);
//		logger.info("find by id student --> {}", student.toString());
//		updatedStudent.setStudentId(student.get().getStudentId());
//		studentRepository.save(updatedStudent);
//	}
//	
//	public List<Student> getStudentsByFirstName(String firstName){
//		return studentRepository.findByFirstName(firstName);
//	}
//	
//	public List<Student> getStudentsByLastName(String lastName){
//		return studentRepository.findByLastName(lastName);
//	}
//	
//	public List<Student> getStudentsByDateOfBirth(LocalDate dateOfBirth){
//		return studentRepository.findByDateOfBirth(dateOfBirth);
//	}
//	
//	public List<Student> getStudentsByClassYear(String classYear){
//		return studentRepository.findByClassYear(classYear);
//	}
//	
//	public List<Student> getStudentsByGrade(double grade){
//		return studentRepository.findByGrade(grade);
//	}
//	
//	public List<StudentDto> getStudentsDto(){
//		return studentRepository.fetchStudentsDto();
//	}
	public List<StudentDto> getStudentsDtoByName(String lastName){
		return studentRepository.findByLastName(lastName);
	}

//	public List<StudentDto> findAllDto(){
//		List<Student> students = getAllStudents();
//		logger.info("students: {}", students.toString());
//		List<StudentDto> studentsDto = new ArrayList<>();
//		
//		for (int i=0; i<students.size(); i++) {
//			StudentDto studentDto = StudentMapper.INSTANCE.studentToStudentDto(students.get(i));
//			studentsDto.add(studentDto);
//			logger.info("student dto: {}", studentDto.toString());
//		}
//		return studentsDto;
//	}
	
//	public void test(){
//		List<Student> students = getAllStudents();
//		logger.info("students: {}", students.toString());
//		List<StudentDto> studentsDto = new ArrayList<>();
//		for (int i=0; i<students.size(); i++) {
//			StudentDto studentDto = StudentMapper.INSTANCE.studentToStudentDto(students.get(i));
//			logger.info("student dto: {}", studentDto.toString());
//		}
//	}
	
//	@AfterMapping
	public void after() {
		logger.info("after");
	}
	
	
}
