package com.home.demo.demospringbootapp.auth.register;

import java.time.ZonedDateTime;

public record RegisterResponse(String username, String message, String token, ZonedDateTime timestamp) {

    public static final String OK = "You have successfully registered to the service!";
    public static final String ERROR = "Something went wrong!";

}
