package com.home.demo.demospringbootapp.controllers;

import com.home.demo.demospringbootapp.UserDaoTemp;
import com.home.demo.demospringbootapp.auth.JwtUtilities;
import com.home.demo.demospringbootapp.dto.AuthenticationRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;
  private final UserDaoTemp userDao;
  private final JwtUtilities jwtUtils;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody AuthenticationRequest request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

    final UserDetails user = userDao.findUserByEmail(request.getEmail());

    if (user != null ) {
      return ResponseEntity.ok(jwtUtils.generateToken(user));
    } else {
      return ResponseEntity.status(400).body("Error!");
    }

  }

}
