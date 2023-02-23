package com.home.demo.demospringbootapp.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.home.demo.demospringbootapp.dto.StudentDto;
import com.home.demo.demospringbootapp.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID>{

	public Student findByStudentId(UUID studentId);
	
	public List<Student> findByFirstName(String firstName);
	
	public List<Student> findByLastName(String lastName);
	
	public List<Student> findByDateOfBirth(LocalDate dateOfBirth);
	
	public List<Student> findByClassYear(int classYear);
	
	public List<Student> findByGrade(double grade);
	
	@Query("SELECT new com.home.demo.demospringbootapp.dto.StudentDto("
			+ "sv.professorId, CONCAT(sv.firstName, ' ', sv.lastName)) "
			+ " FROM Student s INNER JOIN s.supervisor sv WHERE s.studentId = :id")
	public StudentDto fetchSupervisor(@Param("id") UUID studenId) throws NullPointerException;
	
	@Query("SELECT new com.home.demo.demospringbootapp.dto.StudentDto("
			+ "p.professorId, CONCAT(p.firstName, ' ', p.lastName)) "
			+ "FROM Professor p WHERE p.professorId = :id")
	public StudentDto fetchProfessor(@Param("id") UUID supervisorId) throws NullPointerException;

}

