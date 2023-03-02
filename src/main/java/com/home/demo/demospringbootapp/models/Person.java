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
	
	public Person(String firstName, String lastName, LocalDate dateOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

}
