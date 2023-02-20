package com.home.demo.demospringbootapp.dto.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.home.demo.demospringbootapp.dto.StudentDto;
import com.home.demo.demospringbootapp.entities.Student;

public interface StudentDtoRepository extends JpaRepository<Student, UUID>{
	
//	@Query("SELECT new com.home.demo.demospringbootapp.models.StudentDto("
//			+ "s.studentId, s.firstName, s.lastName, s.dateOfBirth, s.classYear, s.grade, "
//			+ "sv.professorId, sv.firstName, sv.lastName) "
//			+ " FROM Student s LEFT JOIN s.supervisor sv")
//	public List<StudentDto> fetchSupervisor();
//	
//	@Query("SELECT new com.home.demo.demospringbootapp.models.StudentDto("
//			+ " FROM Student s INNER JOIN s.supervisor sv"
//			
//			)

}
