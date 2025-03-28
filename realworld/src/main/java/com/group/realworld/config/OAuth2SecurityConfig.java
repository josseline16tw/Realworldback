package com.group.realworld.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@Order(1)
public class OAuth2SecurityConfig {
    @Value("${okta.oauth2.issuer}")
    private String issuer;
    @Value("${okta.oauth2.client-id}")
    private String clientId;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/oauth2/**")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users", "/api/users/login").permitAll() // Allow access to these endpoints
                        .anyRequest().authenticated()
                )

                //.oauth2Login(withDefaults());
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("http://localhost:3000/", true) // react route
                );
        return http.build();
    }

    private LogoutHandler logoutHandler() {
        return (request, response, authentication) -> {
            try {
                String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
                response.sendRedirect(issuer + "v2/logout?client_id=" + clientId + "&returnTo=" + "http://localhost:3000/");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
