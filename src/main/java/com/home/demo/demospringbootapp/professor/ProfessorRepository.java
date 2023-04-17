package com.home.demo.demospringbootapp.professor;

import java.util.List;
import java.util.UUID;

import com.home.demo.demospringbootapp.student.SupervisingStudentDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfessorRepository extends JpaRepository<Professor, UUID> {
	
	List<Professor> findAll(Specification<Professor> spec);
	
	@Query("SELECT new com.home.demo.demospringbootapp.student.SupervisingStudentDto("
			+ "sv.studentId, sv.studentFirstName, sv.studentLastName, s.classYear, s.grade) "
			+ " FROM Professor p "
			+ " INNER JOIN Supervising sv on sv.supervisorId = p.professorId "
			+ " INNER JOIN Student s on s.studentId = sv.studentId "
			+ " WHERE p.professorId = :id")
	List<SupervisingStudentDto> fetchSupervisingStudents(@Param("id") UUID id) throws NullPointerException;

	@Query("SELECT new com.home.demo.demospringbootapp.professor.TeachingCourseDto("
			+ "c.courseId, c.courseName, c.status) "
			+ " FROM Professor p "
			+ " INNER JOIN Course c on c.professor.professorId = p.professorId "
			+ " WHERE p.professorId = :id")
	List<TeachingCourseDto> fetchTeachingCourses(@Param("id") UUID id) throws NullPointerException;

	@Query("select count(c) from Professor p " +
			" inner join Course c on c.professor.professorId = p.professorId " +
			" where p.professorId = :professorId")
	int countProfessorOccurrences(@Param("professorId") UUID professorId);
	
}
