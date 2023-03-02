package com.home.demo.demospringbootapp.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.home.demo.demospringbootapp.models.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProfessorDto extends Person{
	
	private UUID professorId;
	private String title;
	private int numberOfTeachingCourses;
	
	//TODO
//	private List<Course> listOfCourses;
	
	private List<StudentDto> listOfSupervisingStudents;
	
	public ProfessorDto(UUID professorId, String firstName, String lastName, LocalDate dateOfbirth, String title,
			int numberOfTeachingCourses, List<StudentDto> listOfSupervisingStudents) {
		super(firstName, lastName, dateOfbirth);
		this.professorId = professorId;
		this.title = title;
		this.numberOfTeachingCourses = numberOfTeachingCourses;
//		this.listOfCourses = listOfCourses;
		this.listOfSupervisingStudents = listOfSupervisingStudents;
	}

}
