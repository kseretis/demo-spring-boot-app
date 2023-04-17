package com.home.demo.demospringbootapp.professor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.home.demo.demospringbootapp.student.SupervisingStudentDto;
import com.home.demo.demospringbootapp.specifications.GenericSpecification;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private GenericSpecification<Professor> professorSpecification;

	public List<ProfessorDto> getProfessors(Map<String, String> params){
		// Filter and find the professors
		List<Professor> professors = professorRepository.findAll(professorSpecification.specifyFilters(params));
		log.info("Professors: {}", Arrays.toString(professors.toArray()));

		// Map to DTO
		List<ProfessorDto> professorsDTO = professors.stream().map(ProfessorMapper.INSTANCE::toProfessorDto).collect(Collectors.toList());
		professorsDTO.forEach(this::fetchStudentsAndCourses);
		log.info("Professors (DTO): {}", Arrays.toString(professorsDTO.toArray()));

		return professorsDTO;
	}

	public ProfessorDto getProfessor(UUID id) {
		ProfessorDto professor = ProfessorMapper.INSTANCE.toProfessorDto(professorRepository.findById(id).get());
		fetchStudentsAndCourses(professor);
		log.info("Professor (DTO): {}", professor);
		return professor;
	}

	@Transactional
	public void addProfessor(ProfessorDto professorDto) {
		Professor newProfessor = ProfessorMapper.INSTANCE.toProfessor(professorDto);
		professorRepository.save(newProfessor);
		log.info("Professor mapped & added: {}", newProfessor);
	}

	@Transactional
	public void updateProfessor(UUID id, @NotNull ProfessorDto professorDto) {
		professorDto.setProfessorId(professorRepository.findById(id).get().getProfessorId());
		log.info("Professor (DTO) found: {}", professorDto.toString());
		Professor updatedProfessor = ProfessorMapper.INSTANCE.toProfessor(professorDto);
		professorRepository.save(updatedProfessor);
		log.info("Professor updated: {}", updatedProfessor.toString());
//		updateTeachingCourses(updatedProfessor);
	}

	@Transactional
	private void fetchStudentsAndCourses(@NotNull ProfessorDto professor) {
		List<SupervisingStudentDto> students = professorRepository.fetchSupervisingStudents(professor.getProfessorId());
		log.info("list of students: {}", Arrays.toString(students.toArray()));
		professor.setListOfSupervisingStudents(students);

		List<TeachingCourseDto> courses = professorRepository.fetchTeachingCourses(professor.getProfessorId());
		log.info("list of courses: {}", Arrays.toString(courses.toArray()));
		professor.setListOfCourses(courses);
	}

	@Transactional
	private void updateTeachingCourses(@NotNull Professor professor) {
		professor.setTeachingCourses(professorRepository.countProfessorOccurrences(professor.getProfessorId()));
		log.info("Professor teaching course updated to : {}", professor.getTeachingCourses());
		professorRepository.save(professor);
	}

}
