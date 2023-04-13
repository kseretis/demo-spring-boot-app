package com.home.demo.demospringbootapp.dto.auth;

public record AuthenticationResponse(String token, String status){

    public static final String OK = "OK";
    public static final String ERROR = "ERROR";

}
