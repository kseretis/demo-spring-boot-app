package com.home.demo.demospringbootapp.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.home.demo.demospringbootapp.entities.Professor;

public interface ProfessorSpecifications {

    static Specification<Professor> withProperty(String propertyName, Object value) {
        return (root, query, criteriaBuilder) -> 
        	criteriaBuilder.equal(root.get(propertyName), value);
    }
    
    static Specification<Professor> nameLike(String name, String value) {
        return (root, query, criteriaBuilder) -> 
        	criteriaBuilder.like(root.get(name), "%" + value + "%");
    }

    
}
