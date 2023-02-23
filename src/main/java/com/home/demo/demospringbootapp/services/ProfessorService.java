package com.home.demo.demospringbootapp.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.juli.JdkLoggerFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.demo.demospringbootapp.dto.ProfessorDto;
import com.home.demo.demospringbootapp.entities.Professor;
import com.home.demo.demospringbootapp.mappers.ProfessorMapper;
import com.home.demo.demospringbootapp.repositories.ProfessorRepository;

@Service
public class ProfessorService {
	
	private Logger logger = LoggerFactory.getLogger(ProfessorService.class);
	
	@Autowired
	private ProfessorRepository professorRepository;

	public List<ProfessorDto> getAllProfessors(){
//		List<Professor> professors = new ArrayList<>();
//		professorRepository.findAll().forEach(professors::add);
//		return professors;
		
		
		List<Professor> professors = professorRepository.findAll();
		
		List<ProfessorDto> professorsDto = professors.stream().map(ProfessorMapper.INSTANCE::toProfessorDto)
												.collect(Collectors.toList());
		professorsDto.forEach(professor -> {
			professor.setListOfSupervisingStudents(
					professorRepository.fetchSupervisingStudents(professor.getProfessorId()));
		});
		logger.info("Professors DTO: {}", Arrays.toString(professorsDto.toArray()));
		return professorsDto;
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
