package com.home.demo.demospringbootapp.courses;

import java.util.List;
import java.util.UUID;

import com.home.demo.demospringbootapp.students.Student;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID courseId;
	private String courseName;
	private int coveredSeats;
	private int maxStudents;
	private UUID professorId;
	
	@OneToMany(mappedBy = "studentId")
	private List<Student> students;
	
	public Course() {}
	
	public Course(String courseName, int coveredSeats, int maxStudents) {
		this.courseName = courseName;
		this.coveredSeats = coveredSeats;
		this.maxStudents = maxStudents;
	}

	public UUID getCourseID() {
		return courseId;
	}

	public void setCourseID(UUID courseId) {
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

	public UUID getProfessorId() {
		return professorId;
	}

	public void setProfessorId(UUID professorId) {
		this.professorId = professorId;
	}

	@Override
	public String toString() {
		return "Course [courseID=" + courseId + ", courseName=" + courseName + ", coveredSeats=" + coveredSeats
				+ ", maxStudents=" + maxStudents + ", professorId=" + professorId + "]";
	}
	
}
