package com.home.demo.demospringbootapp.dto;

import com.home.demo.demospringbootapp.enums.CourseStatus;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
		super();
		this.courseName = courseName;
		this.coveredSeats = coveredSeats;
		this.maxSeats = maxSeats;
		this.status = status;
		this.professorName = professorName;
	}

}
