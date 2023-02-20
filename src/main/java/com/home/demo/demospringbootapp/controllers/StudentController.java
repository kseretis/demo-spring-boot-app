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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.demo.demospringbootapp.entities.Student;
import com.home.demo.demospringbootapp.dto.StudentDto;
import com.home.demo.demospringbootapp.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;

//	@GetMapping("/")
//	public List<StudentDto> getStudentsDto(){
//		return studentService.getStudentsDto();
//	}
	

	@GetMapping("/{var}")
	public List<StudentDto> getStudentsDto(@PathVariable String var){
//		return studentService.findAllDto();
		return studentService.getStudentsDtoByName(var);
		
	}

}
