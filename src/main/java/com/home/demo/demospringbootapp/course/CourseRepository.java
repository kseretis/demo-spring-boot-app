package com.home.demo.demospringbootapp.course;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.home.demo.demospringbootapp.professor.ProfessorDto;

public interface CourseRepository extends JpaRepository<Course, UUID>{

	List<Course> findAll(Specification<Course> spec);

	@Query("SELECT new com.home.demo.demospringbootapp.professor.ProfessorDto( "
			+ " p.professorId, p.firstName, p.lastName, p.dateOfBirth, p.title, p.teachingCourses) "
			+ " FROM Professor p "
			+ " WHERE p.professorId = :id")
	ProfessorDto fetchProfessor(@Param("id") UUID id);

	@Query("select count(c) from Course c where c.professor.professorId = :professorId")
	int countProfessorOccurrences(@Param("professorId") UUID professorId);

}
