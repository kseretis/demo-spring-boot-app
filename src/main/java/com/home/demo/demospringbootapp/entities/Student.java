package com.home.demo.demospringbootapp.entities;

import java.time.LocalDate;
import java.util.UUID;

import com.home.demo.demospringbootapp.models.Person;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends Person{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID studentId;
	private int classYear;
	private double grade;
	
	@OneToOne
	@JoinColumn(name = "supervisor_id", referencedColumnName = "professorId")
	private Professor supervisor; 
	
	public Student() {}
	
	public Student(String firstName, String lastName, LocalDate dateOfBirth, int class_year, double grade) {
		this.classYear = class_year;
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

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", classYear=" + classYear + ", grade=" + grade + ", supervisor="
				+ supervisor + "]";
	}
	
}
