package com.home.demo.demospringbootapp.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.demo.demospringbootapp.dto.StudentDto;
import com.home.demo.demospringbootapp.services.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;

	@GetMapping(value = {"", "/"})
	public List<StudentDto> getStudents(@RequestParam Map<String, String> params){
		return studentService.getStudents(params);
	}
	
	@GetMapping(value = {"", "/"}, params = "studentId")
	public StudentDto getStudent(@RequestParam("studentId") String id){
		return studentService.getStudent(UUID.fromString(id));
	}
	
	@PostMapping(value = {"", "/"})
	public void addStudent(@RequestBody @NonNull StudentDto student) {
		log.info("Student request body: {}", student.toString());
		studentService.addStudent(student);
	}
	
	@PutMapping(value = {"{id}", "/{id}"})
	public void updateStudent(@PathVariable String id, @RequestBody StudentDto student) {
		studentService.updateStudent(UUID.fromString(id), student);
	}
	
}
