package com.home.demo.demospringbootapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    public static final String OK = "OK";
    public static final String ERROR = "ERROR";

    private String token;
    private String status;

}
