package com.home.demo.demospringbootapp.auth;

import com.home.demo.demospringbootapp.auth.authenticate.AuthenticationRequest;
import com.home.demo.demospringbootapp.auth.authenticate.AuthenticationResponse;
import com.home.demo.demospringbootapp.auth.register.RegisterRequest;
import com.home.demo.demospringbootapp.auth.register.RegisterResponse;
import com.home.demo.demospringbootapp.user.User;
import com.home.demo.demospringbootapp.user.Role;
import com.home.demo.demospringbootapp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

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
