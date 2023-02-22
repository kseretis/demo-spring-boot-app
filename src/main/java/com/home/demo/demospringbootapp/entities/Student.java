package com.home.demo.demospringbootapp.entities;

import java.util.UUID;
import com.home.demo.demospringbootapp.models.Person;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends Person{
	
	@Id
	@GeneratedValue
	private UUID studentId;
	private int classYear;
	private double grade;
//	private UUID supervisorId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supervisor_id", referencedColumnName = "professorId")
	private Professor supervisor; 
	
	public Student() {}
	
	public Student(int classYear, double grade) {
		super();
		this.classYear = classYear;
		this.grade = grade;
	}

	public UUID getStudentId() {
		return studentId;
	}

	public void setStudentId(UUID studentId) {
		this.studentId = studentId;
	}

	public int getClassYear() {
		return classYear;
	}

	public void setClassYear(int classYear) {
		this.classYear = classYear;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	public Professor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Professor supervisor) {
		this.supervisor = supervisor;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() 
				+ ", getDateOfBirth()=" + getDateOfBirth() + ", classYear=" + classYear + ", grade=" + grade 
				+ ", supervisor=" + supervisor + "]";			 
	}
	
}
