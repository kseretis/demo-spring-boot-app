package com.home.demo.demospringbootapp.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Person {
	
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@JsonIgnore
	public String getFullName() {
		return firstName + " " + lastName;
	}

}
