package com.home.demo.demospringbootapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeScreen {

	@GetMapping(value = "/home")
	public String getWelcomeScreen() {
		return "Kalws ta paidia!!!!!";
	}
}
