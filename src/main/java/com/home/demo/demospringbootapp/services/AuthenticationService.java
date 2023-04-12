package com.home.demo.demospringbootapp.services;

import com.home.demo.demospringbootapp.auth.JwtUtilities;
import com.home.demo.demospringbootapp.dto.AuthenticationRequest;
import com.home.demo.demospringbootapp.dto.AuthenticationResponse;
import com.home.demo.demospringbootapp.entities.User;
import com.home.demo.demospringbootapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtilities jwtUtils;

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
