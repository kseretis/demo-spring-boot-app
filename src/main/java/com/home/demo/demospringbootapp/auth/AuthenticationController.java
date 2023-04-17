package com.home.demo.demospringbootapp.auth;

import com.home.demo.demospringbootapp.auth.authenticate.AuthenticationRequest;
import com.home.demo.demospringbootapp.auth.authenticate.AuthenticationResponse;
import com.home.demo.demospringbootapp.auth.register.RegisterRequest;
import com.home.demo.demospringbootapp.auth.register.RegisterResponse;
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
  public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(authenticationService.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
    log.info("***!!!*** - Authentication in progress for user: {}", request.username());
    try {
      return ResponseEntity.ok(authenticationService.authenticate(request));
    } catch (Exception ex) {
      return ResponseEntity.status(400).body(new AuthenticationResponse(null, AuthenticationResponse.ERROR));
    }
  }

}
