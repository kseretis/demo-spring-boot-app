package com.home.demo.demospringbootapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth/home-secure")
public class SecureHomeController {

	@GetMapping
	public ResponseEntity<String> home(Principal principal) {
		return ResponseEntity.ok("Hello "+ principal.getName() +" from a secure point!");
	}

}
