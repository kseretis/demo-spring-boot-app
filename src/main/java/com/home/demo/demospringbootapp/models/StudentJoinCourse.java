package com.home.demo.demospringbootapp.models;

public class StudentJoinCourse {
	
	private String firstName;
	private String lastName;
	private double grade;
	private String courseName;
	
	public StudentJoinCourse() {}
	
	public StudentJoinCourse(String firstName, String lastName, double grade, String courseName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.grade = grade;
		this.courseName = courseName;
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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Override
	public String toString() {
		return "StudentJoinCourse [firstName=" + firstName + ", lastName=" + lastName + ", grade=" + grade
				+ ", courseName=" + courseName + "]";
	}

}
