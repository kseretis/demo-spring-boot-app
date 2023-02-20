package com.home.demo.demospringbootapp.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.home.demo.demospringbootapp.entities.Course;
import com.home.demo.demospringbootapp.dto.CourseDto;

public interface CourseRepository extends JpaRepository<Course, UUID>{
	
	public List<Course> findByCourseName(String courseName);
	
//	@Query("SELECT new com.home.demo.demospringbootapp.models.CourseDto( "
//			+ "c.courseId, c.courseName, c.coveredSeats, c.maxStudents, "
//			+ "CONCAT(p.firstName, ' ', p.lastName) )"
//			+ " FROM Course c LEFT JOIN c.professor p")
//	public List<CourseDto> fetchCoursesAndProfessorNames();
	
}
