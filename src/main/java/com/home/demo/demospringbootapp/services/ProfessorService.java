package com.home.demo.demospringbootapp.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.home.demo.demospringbootapp.dto.ProfessorDto;
import com.home.demo.demospringbootapp.dto.StudentDto;
import com.home.demo.demospringbootapp.entities.Professor;
import com.home.demo.demospringbootapp.mappers.ProfessorMapper;
import com.home.demo.demospringbootapp.repositories.ProfessorRepository;
import com.home.demo.demospringbootapp.specifications.ProfessorSpecifications;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;

	public List<ProfessorDto> getProfessors(Map<String, String> params){
	
		// Filter and find the professors		
		List<Professor> professors = professorRepository.findAll(specifyFilters(params));
		log.info("Professors: {}", Arrays.toString(professors.toArray()));
		
		// Map to DTO
		List<ProfessorDto> professorsDTO = professors.stream().map(ProfessorMapper.INSTANCE::toProfessorDto).collect(Collectors.toList());
		
		// Fetch supervising students
		professorsDTO.forEach( professor -> {
			List<StudentDto> students = professorRepository.fetchSupervisingStudents(professor.getProfessorId());
			log.info("list of students: {]", Arrays.toString(students.toArray()));
			professor.setListOfSupervisingStudents(students);
		});
		log.info("Professors (DTO) found: {}", Arrays.toString(professorsDTO.toArray()));
		
		return professorsDTO;
	}
	
	public ProfessorDto getProfessor(UUID id) {
		ProfessorDto professor = ProfessorMapper.INSTANCE.toProfessorDto(professorRepository.findById(id).get());
		log.info("Professor (DTO) found: {}", professor.toString());
		
		try {
			List<StudentDto> students = professorRepository.fetchSupervisingStudents(professor.getProfessorId());
			log.info("Supervising students: {}", Arrays.toString(students.toArray()));
			professor.setListOfSupervisingStudents(students);
		} catch (NullPointerException ex) {}
		
		return professor;
	}
	
	public void addProfessor(ProfessorDto professorDto) {
		Professor newProfessor = ProfessorMapper.INSTANCE.toProfessor(professorDto);
		professorRepository.save(newProfessor);
		log.info("Professor mapped & added: {}", newProfessor);
	}
	
	public void updateProfessor(UUID id, ProfessorDto professorDto) {
		professorDto.setProfessorId(professorRepository.findById(id).get().getProfessorId());
		log.info("Professor (DTO) found: {}", professorDto.toString());	
		Professor updatedProfessor = ProfessorMapper.INSTANCE.toProfessor(professorDto);
		professorRepository.save(updatedProfessor);
		log.info("Professor updated: {}", updatedProfessor.toString());
	}
	
	private Specification<Professor> specifyFilters(Map<String, String> params) {
		Specification<Professor> spec = Specification.where(null);
			
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
		
		return spec;
	}
}
