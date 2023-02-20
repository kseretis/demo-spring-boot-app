package com.home.demo.demospringbootapp.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID studentId;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String classYear;
	private double grade;
	private UUID supervisorId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id", referencedColumnName = "courseId")
	private Course course;
	
	private List<String> courses;
	
	public Student() {}
	
	public Student(String firstName, String lastName, LocalDate dateOfBirth, String class_year, double grade) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.classYear = class_year;
		this.grade = grade;
	}

	public UUID getStudentId() {
		return studentId;
	}

	public void setStudentId(UUID studentId) {
		this.studentId = studentId;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getClassYear() {
		return classYear;
	}

	public void setClassYear(String classYear) {
		this.classYear = classYear;
	}

	public UUID getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(UUID supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
	

	public List<String> getCourses() {
		return courses;
	}

	public void setCourses(List<String> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", classYear=" + classYear + ", grade=" + grade + ", supervisorId="
				+ supervisorId + ", course=" + course + ", courses=" + courses + "]";
	}
	
}
