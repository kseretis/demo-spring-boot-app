package com.home.demo.demospringbootapp.dto;

import java.util.UUID;

public class CourseDto {
	
	private UUID courseId;
	private String courseName;
	private int coveredSeats;
	private int maxStudents;
	private String professorName;
	
	public CourseDto(UUID courseId, String courseName, int coveredSeats, int maxStudents, String professorName) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.coveredSeats = coveredSeats;
		this.maxStudents = maxStudents;
		this.professorName = professorName;
	}

	public UUID getCourseId() {
		return courseId;
	}

	public void setCourseId(UUID courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCoveredSeats() {
		return coveredSeats;
	}

	public void setCoveredSeats(int coveredSeats) {
		this.coveredSeats = coveredSeats;
	}

	public int getMaxStudents() {
		return maxStudents;
	}

	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

}
