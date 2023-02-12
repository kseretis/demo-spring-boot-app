package com.home.demo.demospringbootapp.students;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer>{

	public List<Student> findByFirstName(String firstName);
	public List<Student> findByLastName(String lastName);
	public List<Student> findByAge(int age);
	public List<Student> findByClassYear(String classYear);
	public List<Student> findByGrade(double grade);
	
}
