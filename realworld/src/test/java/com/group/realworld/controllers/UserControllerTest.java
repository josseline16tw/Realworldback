package com.group.realworld.controllers;



import com.group.realworld.controllers.requestdtos.RegisterRequestBody;
import com.group.realworld.controllers.responsedtos.UserResponseBody;
import com.group.realworld.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest {
    private UserService userService;

    UserController userController;

    public UserControllerTest() {
        this.userService = mock(UserService.class);
        this.userController = new UserController(userService);
    }

    @Test
    public void shouldReturnCreatedUser(){
        RegisterRequestBody requestBody = new RegisterRequestBody("juanjaun","juan@juan.com","1234");
        UserResponseBody responseBody = new UserResponseBody("juanjaun","juan@juan.com",null,null,null);
        when(userService.registerUser(requestBody)).thenReturn(responseBody);
        ResponseEntity<UserResponseBody> resultado = (ResponseEntity<UserResponseBody>) userController.registerUser(requestBody);
        var email = resultado.getBody().email();
        assertNotNull(resultado);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertNotNull(email);

    }

    @Test
    public void shouldReturnExistingEmail(){
        RegisterRequestBody requestBody = new RegisterRequestBody("juanjaun","juan@juan.com","1234");
        UserResponseBody responseBody = new UserResponseBody(null,null,null,null,null);
        when(userService.registerUser(requestBody)).thenReturn(responseBody);
        ResponseEntity<Map<String, String>> resultado = (ResponseEntity<Map<String, String>>) userController.registerUser(requestBody);
        String mensaje =  resultado.getBody().get("mensaje");
        assertEquals("El usuario ya existe",mensaje);
    }

}
