package com.home.demo.demospringbootapp.dto;

import java.util.UUID;

import com.home.demo.demospringbootapp.entities.Course;
import com.home.demo.demospringbootapp.entities.Course.CourseStatus;

public class CourseDto {
	
	private UUID courseId;
	private String courseName;
	private int coveredSeats;
	private int maxSeats;
	private Course.CourseStatus status;
	private String professorName;
	
	public CourseDto() {}
	
	public CourseDto(String courseName, int coveredSeats, int maxSeats, CourseStatus status, 
				String professorName) {
		super();
		this.courseName = courseName;
		this.coveredSeats = coveredSeats;
		this.maxSeats = maxSeats;
		this.status = status;
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

	public int getMaxSeats() {
		return maxSeats;
	}

	public void setMaxSeats(int maxSeats) {
		this.maxSeats = maxSeats;
	}

	public Course.CourseStatus getStatus() {
		return status;
	}

	public void setStatus(Course.CourseStatus status) {
		this.status = status;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	@Override
	public String toString() {
		return "CourseDto [courseId=" + courseId + ", courseName=" + courseName + ", coveredSeats=" + coveredSeats
				+ ", maxSeats=" + maxSeats + ", status=" + status + ", professorName=" + professorName + "]";
	}

}
