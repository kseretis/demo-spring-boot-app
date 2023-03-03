package com.home.demo.demospringbootapp.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.demo.demospringbootapp.entities.Course;
import com.home.demo.demospringbootapp.entities.Student;
import com.home.demo.demospringbootapp.mappers.CourseMapper;
import com.home.demo.demospringbootapp.mappers.ProfessorMapper;
import com.home.demo.demospringbootapp.mappers.StudentMapper;
import com.home.demo.demospringbootapp.dto.CourseDto;
import com.home.demo.demospringbootapp.dto.ProfessorDto;
import com.home.demo.demospringbootapp.dto.StudentDto;
import com.home.demo.demospringbootapp.repositories.CourseRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public List<CourseDto> getCourses() {
		List<Course> courses = courseRepository.findAll();
		log.info("Courses : {}", Arrays.toString(courses.toArray()));
		List<CourseDto> coursesDto = courses.stream().map(CourseMapper.INSTANCE::toCourseDto)
					.collect(Collectors.toList());
		log.info("Courses (DTO) : {}", Arrays.toString(coursesDto.toArray()));
		return coursesDto;
	}
	
	public void addCourse(CourseDto courseDto) {
//		if (courseDto.getSupervisorId() != null) {
//			studentDto.updateSupervisorInfo(studentRepository
//					.fetchProfessor(studentDto.getStudentId()));
//		}
//		logger.info("Cosupervisor matched: {}", studentDto.toString());
		
		Course newCourse= CourseMapper.INSTANCE.toCourse(courseDto);
		courseRepository.save(newCourse);
		log.info("Course mapped & added: {}", newCourse.toString());
	}
	
	
	
	public void updateCourse(UUID id, CourseDto courseDto) {
		// Set course ID
		courseDto.setCourseId(courseRepository.findById(id).get().getCourseId());
		log.info("Course (DTO) found: {}", courseDto.toString());
		
		// Map to course DTO
		Course updatedCourse = CourseMapper.INSTANCE.toCourse(courseDto);
		log.info("Course mapped: {}", updatedCourse.toString());
		// Fetch Professor info & map them to relevant Course
		ProfessorDto professorDto = courseRepository.fetchProfessor(updatedCourse.getCourseId());
		log.info("Professor (DTO) found: {]", professorDto.toString());
		updatedCourse.setProfessor(ProfessorMapper.INSTANCE.toProfessor(professorDto));
		log.info("Course found: {}", updatedCourse.toString());
		
		// Update
		courseRepository.save(updatedCourse);
		log.info("Course updated : {}", updatedCourse.toString());
	}
}
