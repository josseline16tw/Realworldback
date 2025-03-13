package com.group.realworld.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig { //TODO INVESTIGAR USO
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Correct way to disable CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users", "/api/users/login", "/api/articles").permitAll() // Allow access to these endpoints
                        .anyRequest().authenticated() // Protect all other endpoints
                );

        return http.build();
    }

}
