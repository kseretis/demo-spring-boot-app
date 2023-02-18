package com.home.demo.demospringbootapp.courses;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;

	public List<Course> getCourses() {
		List<Course> courses = new ArrayList<>();
		courseRepository.findAll().forEach(courses::add);
		return courses;
	}
	
	public Course getCourse(UUID courseId) {
		return courseRepository.findById(courseId).get();
	}
	
	public List<Course> getCoursesByName(String name){
		return courseRepository.findByCourseName(name);
	}
	
	public void addCourse(Course course) {
		courseRepository.save(course);
	}
	
	public void udpateCourse(UUID courseId, Course course) {
		course.setCourseID(courseId);
		courseRepository.save(course);
	}
}
