package com.home.demo.demospringbootapp.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import com.home.demo.demospringbootapp.models.Person;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "professors")
public class Professor extends Person{

	@Id
	@GeneratedValue
	private UUID professorId;
	private String title;
	private int teachingCourses;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Student> students;
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private List<Course> courses;
	
	public Professor(String firstName, String lastName, LocalDate dateOfBirth, String title, int teachingCourses) {
		super(firstName, lastName, dateOfBirth);
		this.title = title;
		this.teachingCourses = teachingCourses;
	}
	
	public Professor(String firstName, String lastName, LocalDate dateOfBirth, String title, int teachingCourses, List<Student> students) {
		super(firstName, lastName, dateOfBirth);
		this.title = title;
		this.teachingCourses = teachingCourses;
		this.students = students;
	}
	
}
