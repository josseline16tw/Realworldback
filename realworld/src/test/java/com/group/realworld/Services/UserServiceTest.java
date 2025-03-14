package com.group.realworld.Services;

import com.group.realworld.controllers.requestdtos.RegisterRequestBody;
import com.group.realworld.controllers.responsedtos.UserResponseBody;
import com.group.realworld.models.User;
import com.group.realworld.models.jwt.JwtUtil;
import com.group.realworld.repositories.PostgresUserRepository;
import com.group.realworld.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private PostgresUserRepository userRepository;
    private UserService userService;
    private JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;
    User user = new User(UUID.randomUUID(),"juanjuan","juan@juan.com","1234");
    RegisterRequestBody requestBody = new RegisterRequestBody("juanjaun","juan@juan.com","1234");

    @BeforeEach
    public void setUp() {
        jwtUtil = mock(JwtUtil.class);
        passwordEncoder = mock(PasswordEncoder.class);
        when(passwordEncoder.encode(anyString())).thenReturn("encoded_password");
        when(jwtUtil.generateToken(anyString())).thenReturn("mocked_token");
        userRepository = mock(PostgresUserRepository.class);
        userService = new UserService(userRepository,jwtUtil,passwordEncoder);
    }

    @Test
    public void shouldReturnExitingPerson(){


        UserResponseBody responseBody = new UserResponseBody(null, null, null, null, null);
        when(userRepository.getUserByEmail("juan@juan.com")).thenReturn(user); // si retorna, ya existe
        UserResponseBody resultado = userService.registerUser(requestBody);

        assertEquals(responseBody.email(),resultado.email());

        //userRepository.saveUser(user); //response body
    }

    @Test
    public void shouldReturnCreatedUser(){
        UserResponseBody responseBody = new UserResponseBody("juanjaun", "juan@juan.com", null, null, null);
        when(userRepository.getUserByEmail("juan@juan.com")).thenReturn(null);
        doNothing().when(userRepository).saveUser(user);
        UserResponseBody resultado = userService.registerUser(requestBody);
        assertEquals(responseBody.email(),resultado.email());

    }
}
