package com.home.demo.demospringbootapp.courses;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, UUID>{
	
	public List<Course> findByCourseName(String courseName);
	
}
