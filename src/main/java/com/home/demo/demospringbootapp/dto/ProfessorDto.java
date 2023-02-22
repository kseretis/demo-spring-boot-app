package com.home.demo.demospringbootapp.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.home.demo.demospringbootapp.entities.Course;
import com.home.demo.demospringbootapp.entities.Student;
import com.home.demo.demospringbootapp.models.Person;

public class ProfessorDto extends Person{
	
	private UUID professorId;
	private String title;
	private int numberOfTeachingCourses;
	
	//TODO
	private List<Course> listOfCourses;
	//TODO
	private List<Student> listOfSupervisingStudents;
	
	public ProfessorDto(String firstName, String lastName, LocalDate dateOfbirth, String title,
			int numberOfTeachingCourses, List<Course> listOfCourses, List<Student> listOfSupervisingStudents) {
		super();
		this.title = title;
		this.numberOfTeachingCourses = numberOfTeachingCourses;
		this.listOfCourses = listOfCourses;
		this.listOfSupervisingStudents = listOfSupervisingStudents;
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

	public int getNumberOfTeachingCourses() {
		return numberOfTeachingCourses;
	}

	public void setNumberOfTeachingCourses(int numberOfTeachingCourses) {
		this.numberOfTeachingCourses = numberOfTeachingCourses;
	}

	public List<Course> getListOfCourses() {
		return listOfCourses;
	}

	public void setListOfCourses(List<Course> listOfCourses) {
		this.listOfCourses = listOfCourses;
	}

	public List<Student> getListOfSupervisingStudents() {
		return listOfSupervisingStudents;
	}

	public void setListOfSupervisingStudents(List<Student> listOfSupervisingStudents) {
		this.listOfSupervisingStudents = listOfSupervisingStudents;
	}

	@Override
	public String toString() {
		return "ProfessorDto [professorId=" + professorId + ", title=" + title + ", numberOfTeachingCourses="
				+ numberOfTeachingCourses + ", listOfCourses=" + listOfCourses + ", listOfSupervisingStudents="
				+ listOfSupervisingStudents + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName()
				+ ", getDateOfBirth()=" + getDateOfBirth() + "]";
	}
	
}
