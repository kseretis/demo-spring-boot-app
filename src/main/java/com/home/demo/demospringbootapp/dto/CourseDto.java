package com.home.demo.demospringbootapp.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.home.demo.demospringbootapp.enums.CourseStatus;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"courseId", "courseName", "status", "coveredSeats", "maxSeats", "professorId", "professorName"})
public class CourseDto {
	private UUID courseId;
	private String courseName;
	private int coveredSeats;
	private int maxSeats;
	private CourseStatus status;
	private String professorName;
	private UUID professorId;
	
	public CourseDto(String courseName, int coveredSeats, int maxSeats, CourseStatus status, 
				String professorName) {
		this.courseName = courseName;
		this.coveredSeats = coveredSeats;
		this.maxSeats = maxSeats;
		this.status = status;
		this.professorName = professorName;
	}

	public CourseDto(UUID courseId, String courseName, CourseStatus status) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.status = status;
	}

}
