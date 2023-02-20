package com.home.demo.demospringbootapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.demo.demospringbootapp.dto.CourseDto;
import com.home.demo.demospringbootapp.services.CourseService;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/courses")
	public List<CourseDto> getCourses(){
		return courseService.getCourses();
	}
	
	
	
	
}
