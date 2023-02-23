package com.home.demo.demospringbootapp.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.home.demo.demospringbootapp.dto.StudentDto;
import com.home.demo.demospringbootapp.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, UUID>{
	
	public Professor findByProfessorId(UUID professorId);

	public List<Professor> findByFirstName(String firstName);
	
	public List<Professor> findByLastName(String lastName);
	
	public List<Professor> findByDateOfBirth(LocalDate dateOfBirth);
	
	public List<Professor> findByTitle(String title);
	
	public List<Professor> findByTeachingCourses(int teachingCourses);
	
	@Query("SELECT new com.home.demo.demospringbootapp.dto.StudentDto("
			+ "s.studentId, s.studentFirstName, s.studentLastName) "
			+ " FROM Professor p INNER JOIN Supervising s "
			+ " on s.supervisorId = p.professorId WHERE p.professorId = :id")
	public List<StudentDto> fetchSupervisingStudents(@Param("id") UUID id);
	
}
