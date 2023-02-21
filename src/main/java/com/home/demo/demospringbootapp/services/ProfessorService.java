package com.home.demo.demospringbootapp.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.demo.demospringbootapp.entities.Professor;
import com.home.demo.demospringbootapp.repositories.ProfessorRepository;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;

	public List<Professor> getAllProfessors(){
		List<Professor> professors = new ArrayList<>();
		professorRepository.findAll().forEach(professors::add);
		return professors;
	}
	
	public Professor getProfessorById(String id){
		return professorRepository.findById(UUID.fromString(id)).get();
	}
	
	public List<Professor> getProfessorsByFirstName(String firstName) {
		return professorRepository.findByFirstName(firstName);
	}
	
	public List<Professor> getProfessorsByLastName(String lastName) {
		return professorRepository.findByLastName(lastName);
	}
	
	public List<Professor> getProfessorsByDateOfBirth(String dateOfBirth) {
		return professorRepository.findByDateOfBirth(LocalDate.parse(dateOfBirth));
	}
	
	public List<Professor> getProfessorsByTitle(String title) {
		return professorRepository.findByTitle(title);
	}
	
	public List<Professor> getProfessorsByTeachingCourses(String teachingCourses) {
		return professorRepository.findByTeachingCourses(Integer.parseInt(teachingCourses));
	}
	
}
