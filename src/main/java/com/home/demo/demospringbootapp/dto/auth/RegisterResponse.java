package com.home.demo.demospringbootapp.dto.auth;

import lombok.*;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {

    public static final String OK = "You have successfully registered to the service!";
    public static final String ERROR = "Something went wrong!";

    private String username;
    private String message;
    private String token;
    private ZonedDateTime timestamp;

}
