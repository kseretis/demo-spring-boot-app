package com.home.demo.demospringbootapp.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.home.demo.demospringbootapp.entities.Student;

public interface StudentSpecifications {
	
    static Specification<Student> withProperty(String propertyName, Object value) {
        return (root, query, criteriaBuilder) -> 
        	criteriaBuilder.equal(root.get(propertyName), value);
    }
    
    static Specification<Student> nameLike(String name, String value) {
        return (root, query, criteriaBuilder) -> 
        	criteriaBuilder.like(root.get(name), "%" + value + "%");
    }
    
}
