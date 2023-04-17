package com.home.demo.demospringbootapp.course;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.home.demo.demospringbootapp.professor.*;
import com.home.demo.demospringbootapp.specifications.GenericSpecification;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private GenericSpecification<Course> courseSpecification;

	public List<CourseDto> getCourses(Map<String, String> params) {
		List<Course> courses = courseRepository.findAll(courseSpecification.specifyFilters(params));
		log.info("Courses : {}", Arrays.toString(courses.toArray()));
		List<CourseDto> coursesDto = courses.stream().map(CourseMapper.INSTANCE::toCourseDto)
					.collect(Collectors.toList());
		log.info("Courses (DTO) : {}", Arrays.toString(coursesDto.toArray()));
		return coursesDto;
	}

	public CourseDto getCourse(UUID id) {
		return CourseMapper.INSTANCE.toCourseDto(courseRepository.findById(id).get());
	}

	@Transactional
	public void addCourse(CourseDto courseDto) {
		Course newCourse = CourseMapper.INSTANCE.toCourse(courseDto);
		log.info("Course mapped: {}", newCourse.toString());

		if (courseDto.getProfessorId() != null) {
			ProfessorDto professorDto = courseRepository.fetchProfessor(courseDto.getProfessorId());
			newCourse.setProfessor(ProfessorMapper.INSTANCE.toProfessor(professorDto));
			log.info("Professor (DTO) found & mapped: {}", newCourse.toString());
		} else {
			newCourse.setProfessor(null);
		}

		courseRepository.save(newCourse);
		log.info("Course added: {}", newCourse.toString());
		updateProfessorTeachingCourses(newCourse.getProfessor());
	}

	@Transactional
	public void updateCourse(UUID id, CourseDto courseDto) {
		// Set course ID
		courseDto.setCourseId(courseRepository.findById(id).get().getCourseId());
		log.info("Course (DTO) found: {}", courseDto.toString());

		// Map to course DTO
		Course updatedCourse = CourseMapper.INSTANCE.toCourse(courseDto);
		log.info("Course mapped: {}", updatedCourse.toString());

		// Fetch Professor info & map them to relevant Course
		ProfessorDto professorDto = courseRepository.fetchProfessor(courseDto.getProfessorId());
		log.info("Professor (DTO) found: {}", professorDto.toString());

		updatedCourse.setProfessor(ProfessorMapper.INSTANCE.toProfessor(professorDto));
		log.info("Course found: {}", updatedCourse.toString());

		courseRepository.save(updatedCourse);
		log.info("Course updated : {}", updatedCourse.toString());

//		if (updatedCourse.getProfessor().getTeachingCourses() == 0)
		updateProfessorTeachingCourses(updatedCourse.getProfessor());
	}

	@Transactional
	private void updateProfessorTeachingCourses(@NotNull Professor professor) {
		professor.setTeachingCourses(courseRepository.countProfessorOccurrences(professor.getProfessorId()));
		log.info("Professor teaching course updated to : {}", professor.getTeachingCourses());
		professorRepository.save(professor);
	}

}
