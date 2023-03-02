package com.home.demo.demospringbootapp.entities;

import java.util.UUID;

import org.hibernate.annotations.Type;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {
	
	public enum CourseStatus{
		FULL, AVAILABLE
	}
	
	@Id
	@GeneratedValue()
	private UUID courseId;
	private String courseName;
	private int coveredSeats;
	private int maxSeats;
	
	@Enumerated(EnumType.STRING)
	private CourseStatus status;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "professor_id", referencedColumnName = "professorId")
	private Professor professor;
	
	public Course() {}

	public Course(String courseName, int coveredSeats, int maxSeats, CourseStatus status,
			Professor professor) {
		super();
		this.courseName = courseName;
		this.coveredSeats = coveredSeats;
		this.maxSeats = maxSeats;
		this.status = status;
		this.professor = professor;
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

	public CourseStatus getStatus() {
		return status;
	}

	public void setStatus(CourseStatus status) {
		this.status = status;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", coveredSeats=" + coveredSeats
				+ ", maxSeats=" + maxSeats + ", status=" + status + ", professor=" + professor + "]";
	}
	
}
