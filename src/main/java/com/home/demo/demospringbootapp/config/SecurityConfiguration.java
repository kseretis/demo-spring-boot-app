package com.home.demo.demospringbootapp.config;

import com.home.demo.demospringbootapp.auth.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final AuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .csrf()
            .disable()
            .authorizeHttpRequests()
            .requestMatchers(ApplicationConfigs.SECURED_ENDPOINTS)
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    //        .logout()
    //        .logoutUrl("/api/v1/auth/logout")
    //        .addLogoutHandler(logoutHandler)
    //        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
  }

}
