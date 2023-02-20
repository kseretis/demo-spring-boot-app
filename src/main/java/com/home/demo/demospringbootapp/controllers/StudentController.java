package com.home.demo.demospringbootapp.controllers;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.demo.demospringbootapp.entities.Student;
import com.home.demo.demospringbootapp.services.StudentService;

@RestController
public class StudentController {
	
	Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;

	// Get all student
	@GetMapping("/students")
	public List<Student> getAllStudents(@RequestParam Map<String, String> params){
		logger.info("params: {}", params.toString());
		return studentService.getAllStudents();
	}
	
	// Post new student
	@PostMapping("/students")
	public void addStudent(@RequestBody Student student) {
		studentService.addStudent(student);
	}
	
	// Put/Update student
	@PutMapping("/students/{id}")
	public void updateStudent(@PathVariable UUID id,@RequestBody Student student) {
		studentService.updateStudent(id, student);
	}

	// Get students by first name
	@GetMapping("/students/firstname/{firstname}")
	public List<Student> getStudentsByFirstName(@PathVariable String firstname){
		return studentService.getStudentsByFirstName(firstname);
	}
	
	// Get students by last name
	@GetMapping("/students/lastname/{lastname}")
	public List<Student> getStudentsByLastName(@PathVariable String lastname){
		return studentService.getStudentsByLastName(lastname);
	}
	
	// Get students by age
	@GetMapping("/students/dateofbirth/{dateofbirth}")
	public List<Student> getStudentsByAge(@PathVariable LocalDate dateobirth){
		return studentService.getStudentsByDateOfBirth(dateobirth);
	}
	
	// Get students by class year
	@GetMapping("/students/classyear/{classyear}")
	public List<Student> getStudentsByClassYear(@PathVariable String classyear){
		return studentService.getStudentsByClassYear(classyear);
	}
	
	// Get students by grade
	@GetMapping("/students/grade/{grade}")
	public List<Student> getStudentsByGrade(@PathVariable double grade){
		return studentService.getStudentsByGrade(grade);
	}
	
//	@GetMapping("/student/join")
//	public List<StudentJoinCourse> getJoin() {
//		return studentService.getStudentsAndCourse();
//	}

}