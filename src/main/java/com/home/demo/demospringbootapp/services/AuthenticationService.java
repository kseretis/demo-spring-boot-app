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
                .username(request.getUsername())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .role(Role.USER)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
        userRepository.findByUsername(user.getUsername()).orElseThrow();

        return RegisterResponse.builder()
                .username(user.getUsername())
                .message(RegisterResponse.OK)
                .token(jwtUtils.generateToken(user))
                .timestamp(ZonedDateTime.now())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        log.info("***!!!*** - User {} found and authenticated!", user.getUsername());

        return AuthenticationResponse.builder()
                        .token(jwtUtils.generateToken(user))
                        .status(AuthenticationResponse.OK)
                        .build();
    }

}
