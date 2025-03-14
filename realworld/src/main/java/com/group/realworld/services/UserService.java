package com.group.realworld.services;

import com.group.realworld.controllers.requestdtos.RegisterRequestBody;
import com.group.realworld.controllers.responsedtos.UserResponseBody;
import com.group.realworld.models.User;
import com.group.realworld.models.jwt.JwtUtil;
import com.group.realworld.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;
    private JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;

    public UserService(@Qualifier("postgresUserRepo") UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseBody registerUser(RegisterRequestBody requestBody){

        if (userRepository.getUserByEmail(requestBody.email()) == null) {
            System.out.println("Despues de funcion");
            User user = new User(UUID.randomUUID(), requestBody.username(), requestBody.email(), passwordEncoder.encode(requestBody.password()));
            userRepository.saveUser(user);

            String token = jwtUtil.generateToken(user.getEmail());

            return new UserResponseBody(user.getUsername(), user.getEmail(), user.getBio(), user.getImage(), token);
        }
        System.out.println("Email already in use");
        return new UserResponseBody(null, null, null, null, null);

    }
}
