package com.home.demo.demospringbootapp.students;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	private Logger logger = LoggerFactory.getLogger(StudentService.class);
	
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getAllStudents(){
		List<Student> students = new ArrayList<>();
		studentRepository.findAll().forEach(students::add);
		return students;
	}
	
	public void addStudent(Student student) {
		studentRepository.save(student);
	}
	
	public void updateStudent(Integer id, Student student) {
		Optional<Student> studentById = studentRepository.findById(id);
		logger.info("find by id student --> {}", studentById.toString());
		student.setId(studentById.get().getId());
		studentRepository.save(student);
	}
	
	public List<Student> getStudentsByFirstName(String firstName){
		return studentRepository.findByFirstName(firstName);
	}
	
	public List<Student> getStudentsByLastName(String lastName){
		return studentRepository.findByLastName(lastName);
	}
	
	public List<Student> getStudentsByAge(int age){
		return studentRepository.findByAge(age);
	}
	
	public List<Student> getStudentsByClassYear(String classYear){
		return studentRepository.findByClassYear(classYear);
	}
	
	public List<Student> getStudentsByGrade(double grade){
		return studentRepository.findByGrade(grade);
	}
	
}
