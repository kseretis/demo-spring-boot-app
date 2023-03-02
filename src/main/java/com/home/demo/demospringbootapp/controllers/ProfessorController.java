package com.home.demo.demospringbootapp.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.demo.demospringbootapp.dto.ProfessorDto;
import com.home.demo.demospringbootapp.services.ProfessorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/professors")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping(value = {"", "/"})
	public List<ProfessorDto> getProfessors(@RequestParam Map<String, String> params){
		return professorService.getProfessors(params);
	}
	
	@GetMapping(value = {"", "/"}, params = "professorId")
	public ProfessorDto getProfessors(@RequestParam("professorId") String id){
		return professorService.getProfessor(UUID.fromString(id));
	}
	
	@PostMapping(value = {"", "/"})
	public void addProfessor(@RequestBody ProfessorDto professor) {
		log.info("Professor request body: {}", professor.toString());
		professorService.addProfessor(professor);
	}

	@PutMapping(value = {"{id}", "/{id}"})
	public void updateProfessor(@PathVariable String id, @RequestBody ProfessorDto professor) {
		professorService.updateProfessor(UUID.fromString(id), professor);
	}

}
