package com.home.demo.demospringbootapp.students;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.home.demo.demospringbootapp.joins.StudentJoinCourse;

public interface StudentRepository extends JpaRepository<Student, UUID>{

//	public Student findByStudentId(UUID studentId);
	public List<Student> findByFirstName(String firstName);
	public List<Student> findByLastName(String lastName);
	public List<Student> findByDateOfBirth(LocalDate dateOfBirth);
	public List<Student> findByClassYear(String classYear);
	public List<Student> findByGrade(double grade);
	
	@Query("SELECT s FROM students s JOIN s.course c")
	public List<Student> findAllandJoin();
		
}
