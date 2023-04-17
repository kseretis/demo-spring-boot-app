package com.home.demo.demospringbootapp.controllers;

import com.home.demo.demospringbootapp.content.Messages;
import org.aspectj.bridge.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/home")
public class HomeController {

    @GetMapping
    public ResponseEntity<String> home(){
        return ResponseEntity.ok(Messages.MSG_WITHOUT_TOKEN);
    }

}
