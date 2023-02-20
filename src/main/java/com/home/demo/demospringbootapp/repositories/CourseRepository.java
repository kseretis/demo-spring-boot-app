package com.home.demo.demospringbootapp.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.demo.demospringbootapp.entities.Course;

public interface CourseRepository extends JpaRepository<Course, UUID>{
	
	public List<Course> findByCourseName(String courseName);
	
}
