package com.home.demo.demospringbootapp.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.home.demo.demospringbootapp.dto.ProfessorDto;
import com.home.demo.demospringbootapp.dto.StudentDto;
import com.home.demo.demospringbootapp.entities.Professor;
import com.home.demo.demospringbootapp.entities.Student;
import com.home.demo.demospringbootapp.mappers.ProfessorMapper;
import com.home.demo.demospringbootapp.mappers.StudentMapper;
import com.home.demo.demospringbootapp.repositories.ProfessorRepository;
import com.home.demo.demospringbootapp.specifications.ProfessorSpecifications;

@Service
public class ProfessorService {
	
	private Logger logger = LoggerFactory.getLogger(ProfessorService.class);
	
	@Autowired
	private ProfessorRepository professorRepository;

	public List<ProfessorDto> getProfessors(Map<String, String> params){
		Specification<Professor> spec = Specification.where(null);
		
		if (params.get("professorId") != null) 
			spec = spec.and(ProfessorSpecifications.withProperty("professorId", UUID.fromString(params.get("professorId"))));
		
		if (params.get("firstName") != null) 
			spec = spec.and(ProfessorSpecifications.nameLike("firstName", params.get("firstName")));
		
		if (params.get("lastName") != null) 
			spec = spec.and(ProfessorSpecifications.nameLike("lastName", params.get("lastName")) );
		
		if (params.get("dateOfBirth") != null) 
			spec = spec.and(ProfessorSpecifications.withProperty("dateOfBirth", LocalDate.parse(params.get("dateOfBirth"))));
		
		if (params.get("title") != null) 
			spec = spec.and(ProfessorSpecifications.withProperty("title", params.get("title")));
		
		if (params.get("teachingCourses") != null) 
			spec = spec.and(ProfessorSpecifications.withProperty("teachingCourses", Integer.parseInt(params.get("teachingCourses"))));
				
		List<ProfessorDto> professors = professorRepository.findAll(spec).stream().
								map(ProfessorMapper.INSTANCE::toProfessorDto).collect(Collectors.toList());
		professors.forEach( professor -> {
			professor.setListOfSupervisingStudents(professorRepository.fetchSupervisingStudents(professor.getProfessorId()));
		});
		return professors;
	}
	
	public ProfessorDto getProfessor(UUID id) {
		ProfessorDto professor = ProfessorMapper.INSTANCE.toProfessorDto(professorRepository.findById(id).get());
		professor.setListOfSupervisingStudents(professorRepository.fetchSupervisingStudents(professor.getProfessorId()));
		return professor;
	}
	
	//TODO create PUT and POST methods
	public void addProfessor(ProfessorDto professorDto) {
		Professor newProfessor = ProfessorMapper.INSTANCE.toProfessor(professorDto);
		professorRepository.save(newProfessor);
		logger.info("Professor mapped & added: {}", newProfessor);
	}
	
	public void updateProfessor(UUID id, ProfessorDto professorDto) {
		professorDto.setProfessorId(professorRepository.findById(id).get().getProfessorId());
		logger.info("Professor (DTO) found: {}", professorDto.toString());	
		Professor updatedProfessor = ProfessorMapper.INSTANCE.toProfessor(professorDto);
		professorRepository.save(updatedProfessor);
		logger.info("Professor updated: {}", updatedProfessor.toString());
	}
}
