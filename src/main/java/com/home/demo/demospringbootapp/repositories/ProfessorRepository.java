package com.home.demo.demospringbootapp.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.home.demo.demospringbootapp.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, UUID>{
	
	public Professor findByProfessorId(UUID professorId);

	public List<Professor> findByFirstName(String firstName);
	
	public List<Professor> findByLastName(String lastName);
	
	public List<Professor> findByDateOfBirth(LocalDate dateOfBirth);
	
	public List<Professor> findByTitle(String title);
	
	public List<Professor> findByTeachingCourses(int teachingCourses);
	
}
