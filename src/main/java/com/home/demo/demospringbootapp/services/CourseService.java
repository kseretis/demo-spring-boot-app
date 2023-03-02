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
import com.home.demo.demospringbootapp.mappers.StudentMapper;
import com.home.demo.demospringbootapp.dto.CourseDto;
import com.home.demo.demospringbootapp.dto.StudentDto;
import com.home.demo.demospringbootapp.repositories.CourseRepository;

@Service
public class CourseService {
	
	Logger logger = LoggerFactory.getLogger(CourseService.class);
	
	@Autowired
	private CourseRepository courseRepository;

	public List<CourseDto> getCourses() {
		List<Course> courses = courseRepository.findAll();
		logger.info("Courses : {}", Arrays.toString(courses.toArray()));
		List<CourseDto> coursesDto = courses.stream().map(CourseMapper.INSTANCE::toCourseDto)
					.collect(Collectors.toList());
		logger.info("Courses (DTO) : {}", Arrays.toString(coursesDto.toArray()));
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
		logger.info("Course mapped & added: {}", newCourse.toString());
	}
	
	
	
	public void updateCourse(UUID id, CourseDto courseDto) {
		courseDto.setCourseId(courseRepository.findById(id).get().getCourseId());
		logger.info("Course (DTO) found: {}", courseDto.toString());
		Course updatedCourse = CourseMapper.INSTANCE.toCourse(courseDto);
		courseRepository.save(updatedCourse);
		logger.info("Course updated : {}", updatedCourse.toString());
	}
}
