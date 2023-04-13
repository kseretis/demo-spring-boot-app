package com.home.demo.demospringbootapp.models;

import java.time.LocalDate;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public class Person {

	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String fullName;
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		updateFullName();
	}

	public Person(String firstName, String lastName, LocalDate dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		updateFullName();
	}

	private void updateFullName() {
		fullName = firstName +" "+ lastName;
	}

}
