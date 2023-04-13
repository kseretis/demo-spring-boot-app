package com.home.demo.demospringbootapp.services;

import com.home.demo.demospringbootapp.auth.JwtUtils;
import com.home.demo.demospringbootapp.dto.auth.AuthenticationRequest;
import com.home.demo.demospringbootapp.dto.auth.AuthenticationResponse;
import com.home.demo.demospringbootapp.dto.auth.RegisterRequest;
import com.home.demo.demospringbootapp.dto.auth.RegisterResponse;
import com.home.demo.demospringbootapp.entities.User;
import com.home.demo.demospringbootapp.enums.Role;
import com.home.demo.demospringbootapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public RegisterResponse register(RegisterRequest request) {

        User user = User.builder()
                .username(request.username())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .role(Role.USER)
                .password(passwordEncoder.encode(request.password()))
                .build();

        userRepository.save(user);
        userRepository.findByUsername(user.getUsername()).orElseThrow();

        return new RegisterResponse(
                user.getUsername(), RegisterResponse.OK, jwtUtils.generateToken(user), ZonedDateTime.now());
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        User user = userRepository.findByUsername(request.username()).orElseThrow();
        log.info("***!!!*** - User {} found and authenticated!", user.getUsername());

        return new AuthenticationResponse(jwtUtils.generateToken(user),AuthenticationResponse.OK);
    }

}
