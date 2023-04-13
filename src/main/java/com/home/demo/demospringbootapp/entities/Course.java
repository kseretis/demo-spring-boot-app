package com.home.demo.demospringbootapp.entities;

import java.util.UUID;
import com.home.demo.demospringbootapp.enums.CourseStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
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
	@OneToOne(fetch = FetchType.LAZY)
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
