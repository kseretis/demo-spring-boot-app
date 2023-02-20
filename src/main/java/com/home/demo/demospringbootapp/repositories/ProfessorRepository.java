package com.home.demo.demospringbootapp.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.demo.demospringbootapp.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, UUID>{
	
	

}
