package com.home.demo.demospringbootapp.dto;

import java.time.LocalDate;
import java.util.UUID;

public class StudentDto {
	
	private UUID studentId;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private int classYear;
	private double grade;
	private UUID supervisorId;
	private String supervisorFirstName;
	private String supervisorLastName;

	public StudentDto(UUID studentId, String firstName, String lastName, LocalDate dateOfBirth, int classYear,
			double grade, UUID supervisorId, String supervisorFirstName, String supervisorLastName) {
		super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.classYear = classYear;
		this.grade = grade;
		this.supervisorId = supervisorId;
		this.supervisorFirstName = supervisorFirstName;
		this.supervisorLastName = supervisorLastName;
	}

	public UUID getStudentId() {
		return studentId;
	}

	public void setStudentId(UUID studentId) {
		this.studentId = studentId;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getClassYear() {
		return classYear;
	}

	public void setClassYear(int classYear) {
		this.classYear = classYear;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public UUID getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(UUID supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getSupervisorFirstName() {
		return supervisorFirstName;
	}

	public void setSupervisorFirstName(String supervisorFirstName) {
		this.supervisorFirstName = supervisorFirstName;
	}

	public String getSupervisorLastName() {
		return supervisorLastName;
	}

	public void setSupervisorLastName(String supervisorLastName) {
		this.supervisorLastName = supervisorLastName;
	}

	@Override
	public String toString() {
		return "StudentDto [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", classYear=" + classYear + ", grade=" + grade + ", supervisorId="
				+ supervisorId + ", supervisorFirstName=" + supervisorFirstName + ", supervisorLastName="
				+ supervisorLastName + "]";
	}

}