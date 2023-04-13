package com.home.demo.demospringbootapp.auth;

import com.home.demo.demospringbootapp.entities.User;
import com.home.demo.demospringbootapp.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

  private final JwtUtils jwtUtils;
  private final UserRepository userRepository;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
          throws ServletException, IOException {

    final String authHeader = request.getHeader(AUTHORIZATION);
    final String jwt;
    final String username;

    if (authHeader == null || !authHeader.startsWith(JwtUtils.BEARER)) {
      filterChain.doFilter(request, response);
      return;
    }

    jwt = authHeader.substring(JwtUtils.BEARER.length());
    username = jwtUtils.extractUsername(jwt);
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      User user = userRepository.findByUsername(username).get();

      if (jwtUtils.isTokenValid(jwt, user)) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        log.info("Valid token: {}", authToken);
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request, response);
  }

}
