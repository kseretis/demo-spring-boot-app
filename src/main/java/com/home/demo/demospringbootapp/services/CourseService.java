package com.home.demo.demospringbootapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.demo.demospringbootapp.entities.Course;
import com.home.demo.demospringbootapp.dto.CourseDto;
import com.home.demo.demospringbootapp.repositories.CourseRepository;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;

	public List<CourseDto> getCourses() {
		return null;
//		return courseRepository.fetchCoursesAndProfessorNames();
	}
}
