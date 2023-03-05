package com.home.demo.demospringbootapp.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.home.demo.demospringbootapp.dto.StudentDto;
import com.home.demo.demospringbootapp.entities.Professor;
import com.home.demo.demospringbootapp.specifications.ProfessorSpecifications;

public interface ProfessorRepository extends JpaRepository<Professor, UUID>, ProfessorSpecifications {
	
	public List<Professor> findAll(Specification<Professor> spec);
	
	@Query("SELECT new com.home.demo.demospringbootapp.dto.StudentDto("
			+ "sv.studentId, sv.studentFirstName, sv.studentLastName, s.classYear, s.grade) "
			+ " FROM Professor p "
			+ " INNER JOIN Supervising sv on sv.supervisorId = p.professorId "
			+ " INNER JOIN Student s on s.studentId = sv.studentId "
			+ " WHERE p.professorId = :id")
	public List<StudentDto> fetchSupervisingStudents(@Param("id") UUID id) throws NullPointerException;
	
}
