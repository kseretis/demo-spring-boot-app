package com.home.demo.demospringbootapp.auth.authenticate;

public record AuthenticationResponse(String token, String status){

    public static final String OK = "OK";
    public static final String ERROR = "ERROR";

}
