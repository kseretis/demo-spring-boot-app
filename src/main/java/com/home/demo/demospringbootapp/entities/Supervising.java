package com.home.demo.demospringbootapp.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "supervising")
public class Supervising {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String supervisorFirstName;
	private String supervisorLastName;
	private UUID supervisorId;
	private String studentFirstName;
	private String studentLastName;
	private UUID studentId;
	
	public Supervising() {}

	public Supervising(String supervisorFirstName, String supervisorLastName, UUID supervisorId,
			String studentFirstName, String studentLastName, UUID studentId) {
		this.supervisorFirstName = supervisorFirstName;
		this.supervisorLastName = supervisorLastName;
		this.supervisorId = supervisorId;
		this.studentFirstName = studentFirstName;
		this.studentLastName = studentLastName;
		this.studentId = studentId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSupervisorFirstName() {
		return supervisorFirstName;
	}

	public void setSupervisorFirstName(String supervisorFirstName) {
		this.supervisorFirstName = supervisorFirstName;
	}

	public String getSupervisorLastName() {
		return supervisorLastName;
	}

	public void setSupervisorLastName(String supervisorLastName) {
		this.supervisorLastName = supervisorLastName;
	}

	public UUID getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(UUID supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public UUID getStudentId() {
		return studentId;
	}

	public void setStudentId(UUID studentId) {
		this.studentId = studentId;
	}

	@Override
	public String toString() {
		return "Supervising [id=" + id + ", supervisorFirstName=" + supervisorFirstName + ", supervisorLastName="
				+ supervisorLastName + ", supervisorId=" + supervisorId + ", studentFirstName=" + studentFirstName
				+ ", studentLastName=" + studentLastName + ", studentId=" + studentId + "]";
	}

}
