package com.home.demo.demospringbootapp.controllers;

import com.home.demo.demospringbootapp.dto.auth.AuthenticationRequest;
import com.home.demo.demospringbootapp.dto.auth.AuthenticationResponse;
import com.home.demo.demospringbootapp.dto.auth.RegisterRequest;
import com.home.demo.demospringbootapp.dto.auth.RegisterResponse;
import com.home.demo.demospringbootapp.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(authenticationService.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
    log.info("***!!!*** - Authentication in progress for user: {}", request.getUsername());
    try {
      return ResponseEntity.ok(authenticationService.authenticate(request));
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(AuthenticationResponse.builder().status(AuthenticationResponse.ERROR).build());
    }
  }

}
