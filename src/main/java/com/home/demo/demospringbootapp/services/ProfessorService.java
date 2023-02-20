package com.home.demo.demospringbootapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.demo.demospringbootapp.entities.Professor;
import com.home.demo.demospringbootapp.repositories.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;

	public List<Professor> getProfessors(){
		List<Professor> professors = new ArrayList<>();
		professorRepository.findAll().forEach(professors::add);
		return professors;
	}
	
}
