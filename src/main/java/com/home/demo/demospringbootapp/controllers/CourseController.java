package com.home.demo.demospringbootapp.controllers;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.demo.demospringbootapp.dto.CourseDto;
import com.home.demo.demospringbootapp.dto.ProfessorDto;
import com.home.demo.demospringbootapp.services.CourseService;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
	
	Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	private CourseService courseService;
	
	@GetMapping(value = {"", "/"})
	public List<CourseDto> getCourses(){
		return courseService.getCourses();
	}
	
	@PostMapping(value = {"", "/"})
	public void addCourse(@RequestBody CourseDto course) {
		logger.info("Course request body: {}", course.toString());
		courseService.addCourse(course);
	}
	
	@PutMapping(value = {"{id}", "/{id}"})
	public void updateCourse(@PathVariable String id, @RequestBody CourseDto course) {
		courseService.updateCourse(UUID.fromString(id), course);
	}
	
	
}
