package com.home.demo.demospringbootapp.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.home.demo.demospringbootapp.models.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class StudentDto extends Person{
	
	private UUID studentId;
	private int classYear;
	private double grade;
	private UUID supervisorId;
	private String supervisorName;
	
	public StudentDto(UUID supervisorId, String supervisorName) {
		this.supervisorId = supervisorId;
		this.supervisorName = supervisorName;
	}
	
	public StudentDto(UUID studentId, String firstName, String lastName, LocalDate dateOfbirth,
							int classYear, double grade) {
		super(firstName, lastName, dateOfbirth);
		this.studentId = studentId;
		this.classYear = classYear;
		this.grade = grade;
	}

	public void updateSupervisorInfo(StudentDto studentDto) {
		if (studentDto != null) {
			supervisorId = studentDto.getSupervisorId();
			supervisorName = studentDto.getSupervisorName();
		}
	}

}
