package com.home.demo.demospringbootapp.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.demo.demospringbootapp.dto.ProfessorDto;
import com.home.demo.demospringbootapp.services.ProfessorService;

@RestController
@RequestMapping("/professors")
public class ProfessorController {
	
	private Logger logger = LoggerFactory.getLogger(ProfessorController.class);
	
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

//	@PostMapping(value = {"", "/"})
//	public void addProfessor(@RequestBody ProfessorDto professor) {
////		professorService.
//	}
	
//	@PutMapping(value = {"", "/"})
//	public void updateProfessor(RequestParam String id, @RequestBody StudentDto student) {
//		studentService.updateStudent(UUID.fromString(id), student);
//	}

}
