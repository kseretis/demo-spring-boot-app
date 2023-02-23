package com.home.demo.demospringbootapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.home.demo.demospringbootapp.dto.ProfessorDto;
import com.home.demo.demospringbootapp.entities.Professor;
import com.home.demo.demospringbootapp.services.ProfessorService;

@RestController
@RequestMapping("/professors")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping("/")
	public List<ProfessorDto> getAllProfessors(){
		return professorService.getAllProfessors();
	}
	
//	@GetMapping("/{id}")
//	public Professor getProfessorById(@PathVariable String id){
//		return professorService.getProfessorById(id);
//	}
//	
//	@GetMapping("/firstname/{firstName}")
//	public List<Professor> getProfessorsByFirstName(@PathVariable String firstName){
//		return professorService.getProfessorsByFirstName(firstName);
//	}
//	
//	@GetMapping("/lastname/{lastName}")
//	public List<Professor> getProfessorsByLastName(@PathVariable String lastName){
//		return professorService.getProfessorsByLastName(lastName);
//	}
//	
//	@GetMapping("/dateofbirth/{dateOfBirth}")
//	public List<Professor> getProfessorsByDateOfBirth(@PathVariable String dateOfBirth){
//		return professorService.getProfessorsByDateOfBirth(dateOfBirth);
//	}
//	
//	@GetMapping("/title/{title}")
//	public List<Professor> getProfessorsByTitle(@PathVariable String title){
//		return professorService.getProfessorsByTitle(title);
//	}
//	
//	@GetMapping("/courses/{courses}")
//	public List<Professor> getProfessorsByTeachingCourses(@PathVariable String courses){
//		return professorService.getProfessorsByTeachingCourses(courses);
//	}

}
