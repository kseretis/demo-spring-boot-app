package com.home.demo.demospringbootapp.specifications;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * A {@link GenericSpecification} class, that it is being initialized passing the class that needs
 * to return the specification object.
 * It is recommended to use @Autowired in the service class.
 * e.g.: private GenericSpecification<MyEntity> myEntitySpecification;
 *
 * @param <T> Entity class
 */
@Slf4j
@Component("genericSpecification")
public class GenericSpecification<T> {

    private Specification<T> specifications;

    public Specification<T> specifyFilters(Map<String, String> params) {
        specifications = Specification.where(null);

        params.forEach( (key, value) -> {
            log.info("Key: {}, Value: {}", key, value);
            specifications = specifications.and(withProperty(key, value));
        });
        return specifications;
    }

    public Specification<T> withProperty(String propertyName, Object value) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(propertyName), value);
    }

//    public Specification<T> nameLike(String name, String value) {
//        return (root, query, criteriaBuilder) ->
//                criteriaBuilder.like(root.get(name), "%" + value + "%");
//    }
}
