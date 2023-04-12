package com.home.demo.demospringbootapp.config;

public interface ApplicationConfigs {

    String[] SECURED_ENDPOINTS = {"/api/v1/auth/**"};
    String[] ENDPOINTS = {"/api/v1/home/**"};

}
