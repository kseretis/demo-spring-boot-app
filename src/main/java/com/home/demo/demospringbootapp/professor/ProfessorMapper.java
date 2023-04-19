package com.home.demo.demospringbootapp.professor;

import com.home.demo.demospringbootapp.course.Course;
import com.home.demo.demospringbootapp.course.CourseDto;
import com.home.demo.demospringbootapp.course.CourseMapper;
import com.home.demo.demospringbootapp.student.Student;
import com.home.demo.demospringbootapp.student.StudentDto;
import com.home.demo.demospringbootapp.student.StudentMapper;
import com.home.demo.demospringbootapp.student.SupervisingStudentDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProfessorMapper {

	ProfessorMapper INSTANCE = Mappers.getMapper(ProfessorMapper.class);

	@Mapping(source = "teachingCourses", target = "numberOfTeachingCourses")
	@Mapping(source = "students", target = "listOfSupervisingStudents" )
	@Mapping(source = "courses", target = "listOfCourses")
	ProfessorDto toProfessorDto(Professor professor);
	
	@InheritInverseConfiguration
	Professor toProfessor(ProfessorDto professorDto);

//	@ProfessorMapperNamed
//	static List<SupervisingStudentDto> mapStudents(List<Student> students) {
//		return null; //StudentMapper.INSTANCE.toStudentDtoList(students);
//	}

//	@Named(value = "mapCourses")
//	default List<CourseDto> mapCourses(List<Course> courses) {
//		return CourseMapper.INSTANCE.toCourseDtoList(courses);
//	}
	
}
