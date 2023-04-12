package com.home.demo.demospringbootapp.repositories;

import com.home.demo.demospringbootapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
