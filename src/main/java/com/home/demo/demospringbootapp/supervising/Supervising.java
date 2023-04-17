package com.home.demo.demospringbootapp.supervising;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
	
	public Supervising(String supervisorFirstName, String supervisorLastName, UUID supervisorId,
			String studentFirstName, String studentLastName, UUID studentId) {
		this.supervisorFirstName = supervisorFirstName;
		this.supervisorLastName = supervisorLastName;
		this.supervisorId = supervisorId;
		this.studentFirstName = studentFirstName;
		this.studentLastName = studentLastName;
		this.studentId = studentId;
	}

}
