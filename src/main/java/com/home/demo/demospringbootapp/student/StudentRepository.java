package com.home.demo.demospringbootapp.student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, UUID> {
	
	Optional<List<Student>> findAll(Specification<Student> spec);

	@Query("SELECT new com.home.demo.demospringbootapp.student.StudentDto("
			+ "sv.professorId, CONCAT(sv.firstName, ' ', sv.lastName)) "
			+ " FROM Student s INNER JOIN s.supervisor sv WHERE s.studentId = :id")
	Optional<StudentDto> fetchSupervisor(@Param("id") UUID studentId);
	
	@Query("SELECT new com.home.demo.demospringbootapp.student.StudentDto("
			+ "p.professorId, CONCAT(p.firstName, ' ', p.lastName)) "
			+ "FROM Professor p WHERE p.professorId = :id")
	Optional<StudentDto> fetchProfessor(@Param("id") UUID supervisorId);

}

