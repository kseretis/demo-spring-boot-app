package com.home.demo.demospringbootapp.repositories;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.home.demo.demospringbootapp.entities.Student;

public interface StudentRepository extends JpaRepository<Student, UUID>{

//	public Student findByStudentId(UUID studentId);
	public List<Student> findByFirstName(String firstName);
	public List<Student> findByLastName(String lastName);
	public List<Student> findByDateOfBirth(LocalDate dateOfBirth);
	public List<Student> findByClassYear(String classYear);
	public List<Student> findByGrade(double grade);
	
//	@Query("SELECT new com.home.demo.demospringbootapp.joins.StudentJoinCourse(s.firstName, s.lastName, s.grade, c.courseName) "
//			+ " FROM Student s LEFT JOIN s.course c")
//	public List<StudentJoinCourse> fetchStudentJoinCourse();
		
}

