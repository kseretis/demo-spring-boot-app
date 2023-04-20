package com.home.demo.demospringbootapp.student;

import java.time.LocalDate;
import java.util.UUID;
import com.home.demo.demospringbootapp.models.Person;
import com.home.demo.demospringbootapp.professor.Professor;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table(name = "students")
public class Student extends Person{

	@Id
	@GeneratedValue
	private UUID studentId;
	private int classYear;
	private double grade;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supervisor_id", referencedColumnName = "professorId")
	private Professor supervisor;
	
	public Student(String firstName, String lastName, LocalDate dateOfBirth, int classYear, double grade) {
		super(firstName, lastName, dateOfBirth);
		this.classYear = classYear;
		this.grade = grade;
	}

	public Professor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Professor supervisor) {
		this.supervisor = supervisor;
	}

}
