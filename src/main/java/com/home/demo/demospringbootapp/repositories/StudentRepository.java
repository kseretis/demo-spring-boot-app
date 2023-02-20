package com.home.demo.demospringbootapp.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.mapstruct.AfterMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.home.demo.demospringbootapp.entities.Student;
import com.home.demo.demospringbootapp.dto.StudentDto;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID>{

//	public Student findByStudentId(UUID studentId);
//	public List<Student> findByFirstName(String firstName);
//	public List<Student> findByLastName(String lastName);
//	public List<Student> findByDateOfBirth(LocalDate dateOfBirth);
//	public List<Student> findByClassYear(String classYear);
//	public List<Student> findByGrade(double grade);
	
	@Query("SELECT new com.home.demo.demospringbootapp.dto.StudentDto("
			+ "s.studentId, s.firstName, s.lastName, s.dateOfBirth, s.classYear, s.grade, "
			+ "sv.professorId, sv.firstName, sv.lastName) "
			+ " FROM Student s LEFT JOIN s.supervisor sv")
	public List<StudentDto> fetchStudentsDto();
	
//	public List<StudentDto> findbyFirstName(String firstName);
	
	
	@Query("SELECT new com.home.demo.demospringbootapp.dto.StudentDto("
			+ "s.studentId, s.firstName, s.lastName, s.dateOfBirth, s.classYear, s.grade, "
			+ "sv.professorId, sv.firstName, sv.lastName) "
			+ " FROM Student s LEFT JOIN s.supervisor sv WHERE s.lastName = ?1")
	public List<StudentDto> findByLastName(String lastName);
		
}

