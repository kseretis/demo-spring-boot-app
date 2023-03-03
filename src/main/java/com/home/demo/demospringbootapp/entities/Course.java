package com.home.demo.demospringbootapp.entities;

import java.util.UUID;

import com.home.demo.demospringbootapp.enums.CourseStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "courses")
public class Course {
	
	@Id
	@GeneratedValue()
	private UUID courseId;
	private String courseName;
	private int coveredSeats;
	private int maxSeats;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private CourseStatus status;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "professor_id", referencedColumnName = "professorId")
	private Professor professor;
	
	public Course(String courseName, int coveredSeats, int maxSeats, CourseStatus status,
			Professor professor) {
		this.courseName = courseName;
		this.coveredSeats = coveredSeats;
		this.maxSeats = maxSeats;
		this.status = status;
		this.professor = professor;
	}
	
}
