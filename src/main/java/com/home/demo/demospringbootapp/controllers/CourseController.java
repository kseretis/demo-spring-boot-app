package com.home.demo.demospringbootapp.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.home.demo.demospringbootapp.dto.CourseDto;
import com.home.demo.demospringbootapp.services.CourseService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping(value = {"", "/"})
	public List<CourseDto> getCourses(@RequestParam Map<String, String> params) {
		return courseService.getCourses(params);
	}

	@GetMapping(value = {"", "/"}, params = "courseId")
	public CourseDto getCourse(@RequestParam("courseId") String id) {
		return courseService.getCourse(UUID.fromString(id));
	}
	
	@PostMapping(value = {"", "/"})
	public void addCourse(@RequestBody @NotNull CourseDto course) {
		log.info("Course request body: {}", course.toString());
		courseService.addCourse(course);
	}
	
	@PutMapping(value = {"{id}", "/{id}"})
	public void updateCourse(@PathVariable String id, @RequestBody CourseDto course) {
		courseService.updateCourse(UUID.fromString(id), course);
	}

}
