package com.home.demo.demospringbootapp.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.home.demo.demospringbootapp.dto.StudentDto;
import com.home.demo.demospringbootapp.entities.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {
	
	List<Student> findAll(Specification<Student> spec);

	@Query("SELECT new com.home.demo.demospringbootapp.dto.StudentDto("
			+ "sv.professorId, CONCAT(sv.firstName, ' ', sv.lastName)) "
			+ " FROM Student s INNER JOIN s.supervisor sv WHERE s.studentId = :id")
	StudentDto fetchSupervisor(@Param("id") UUID studenId) throws NullPointerException;
	
	@Query("SELECT new com.home.demo.demospringbootapp.dto.StudentDto("
			+ "p.professorId, CONCAT(p.firstName, ' ', p.lastName)) "
			+ "FROM Professor p WHERE p.professorId = :id")
	StudentDto fetchProfessor(@Param("id") UUID supervisorId) throws NullPointerException;

}

