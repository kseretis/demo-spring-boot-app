package com.home.demo.demospringbootapp.content;

public interface ApplicationEndpoints {

    String[] SECURED_ENDPOINTS = {"/api/v1/auth/**"};
    String[] ENDPOINTS = {"/api/v1/home/**"};

}
