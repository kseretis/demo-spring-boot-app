package com.home.demo.demospringbootapp.entities;

import java.time.LocalDate;
import java.util.UUID;
import com.home.demo.demospringbootapp.models.Person;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "professors")
public class Professor extends Person{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID professorId;
	private String title;
	private int teachingCourses;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Student student;
	
	public Professor() {}

	public Professor(String firstName, String lastName, LocalDate dateOfBirth, String title, int teachingCourses) {
		super(firstName, lastName, dateOfBirth);
		this.title = title;
		this.teachingCourses = teachingCourses;
	}

	public UUID getProfessorId() {
		return professorId;
	}

	public void setProfessorId(UUID professorId) {
		this.professorId = professorId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTeachingCourses() {
		return teachingCourses;
	}

	public void setTeachingCourses(int teachingCourses) {
		this.teachingCourses = teachingCourses;
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Professor [professorId=" + professorId + ", title=" + title + ", teachingCourses=" + teachingCourses
				+ ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getDateOfBirth()="
				+ getDateOfBirth() + "]";
	}

}
