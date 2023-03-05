package com.home.demo.demospringbootapp.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;

@Component("genericSpecification")
public class GenericSpecification<T> {

    public Specification<T> withProperty(String propertyName, Object value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(propertyName), value);
    }

    public Specification<T> nameLike(String name, String value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(name), "%" + value + "%");
    }


}
