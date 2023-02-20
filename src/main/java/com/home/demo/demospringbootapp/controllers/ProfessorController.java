package com.home.demo.demospringbootapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.demo.demospringbootapp.entities.Professor;
import com.home.demo.demospringbootapp.services.ProfessorService;

@RestController
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping("/professors")
	public List<Professor> getProfessors(){
		return professorService.getProfessors();
	}

}
