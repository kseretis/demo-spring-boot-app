package com.home.demo.demospringbootapp.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.demo.demospringbootapp.dto.StudentDto;
import com.home.demo.demospringbootapp.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;

	@GetMapping("/")
	public List<StudentDto> getAllStudents(){
		return studentService.getAllStudents();
	}
	
	@GetMapping("/{id}")
	public StudentDto getStudentById(@PathVariable String id){
		return studentService.getStudentById(id);
	}
	
	@GetMapping("/lastname/{lastName}")
	public List<StudentDto> getStudentsByLastName(@PathVariable String lastName) {
		return studentService.getStudentsByLastName(lastName);
	}
	
	@GetMapping("/firstname/{firstName}")
	public List<StudentDto> getStudentsByFirstName(@PathVariable String firstName) {
		return studentService.getStudentsByFirstName(firstName);
	}
	
	@GetMapping("/dateofbirth/{dateOfBirth}")
	public List<StudentDto> getStudentsByDateOfBirth(@PathVariable String dateOfBirth) {
		return studentService.getStudentsByDateOfBirth(dateOfBirth);
	}
	
	@GetMapping("/classyear/{classYear}")
	public List<StudentDto> getStudentsByClassYear(@PathVariable String classYear) {
		return studentService.getStudentsByClassYear(classYear);
	}
	
	@GetMapping("/grade/{grade}")
	public List<StudentDto> getStudentsByGrade(@PathVariable String grade) {
		return studentService.getStudentsByGrade(grade);
	}
	
}
