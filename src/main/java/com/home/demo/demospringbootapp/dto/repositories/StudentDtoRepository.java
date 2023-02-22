package com.home.demo.demospringbootapp.dto.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.home.demo.demospringbootapp.dto.StudentDto;
import com.home.demo.demospringbootapp.entities.Student;

@Repository
public interface StudentDtoRepository extends JpaRepository<Student, UUID>{
	
	@Query("SELECT new com.home.demo.demospringbootapp.dto.StudentDto("
			+ "sv.professorId, CONCAT(sv.firstName, ' ', sv.lastName)) "
			+ " FROM Student s INNER JOIN s.supervisor sv WHERE s.studentId = :id")
	public StudentDto fetchSupervisor(@Param("id") UUID studenId) throws NullPointerException;
	
	@Query("SELECT new com.home.demo.demospringbootapp.dto.StudentDto("
			+ "p.professorId, CONCAT(p.firstName, ' ', p.lastName)) "
			+ "FROM Professor p WHERE p.professorId = :id")
	public StudentDto fetchProfessor(@Param("id") UUID supervisorId) throws NullPointerException;

}
