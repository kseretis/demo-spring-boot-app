package com.home.demo.demospringbootapp.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.home.demo.demospringbootapp.entities.Course;
import com.home.demo.demospringbootapp.services.CourseService;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/courses")
	public List<Course> getCourses(){
		return courseService.getCourses();
	}
	
	@GetMapping("/course/{courseId}")
	public Course getCourse(@PathVariable UUID courseId) {
		return courseService.getCourse(courseId);
	}
	
	
	
}
