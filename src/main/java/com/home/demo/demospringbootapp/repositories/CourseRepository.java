package com.home.demo.demospringbootapp.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.home.demo.demospringbootapp.entities.Course;
import com.home.demo.demospringbootapp.dto.ProfessorDto;

public interface CourseRepository extends JpaRepository<Course, UUID>{
	
	@Query("SELECT new com.home.demo.demospringbootapp.dto.ProfessorDto( "
			+ " p.professorId, p.firstName, p.lastName, p.dateOfBirth, p.title, p.teachingCourses) "
			+ " FROM Course c"
			+ " INNER JOIN Professor p on p.professorId = c.professor.professorId "
			+ " WHERE c.courseId = :id")
	public ProfessorDto fetchProfessor(@Param("id") UUID id);
	
}
