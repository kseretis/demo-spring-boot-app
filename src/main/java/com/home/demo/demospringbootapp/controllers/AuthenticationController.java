package com.home.demo.demospringbootapp.controllers;

import com.home.demo.demospringbootapp.dto.AuthenticationRequest;
import com.home.demo.demospringbootapp.dto.AuthenticationResponse;
import com.home.demo.demospringbootapp.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
  public void register(@RequestBody AuthenticationRequest request) {
    //TODO
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
