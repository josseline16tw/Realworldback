package com.group.realworld.controllers;

import com.group.realworld.controllers.requestdtos.LoginRequestBody;
import com.group.realworld.controllers.requestdtos.RegisterRequestBody;
import com.group.realworld.controllers.responsedtos.UserResponseBody;
import com.group.realworld.models.User;
import com.group.realworld.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestBody requestBody){
        UserResponseBody userResponseBody = userService.registerUser(requestBody);
        if (userResponseBody.email() == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("mensaje", "El usuario ya existe"));
        }
        return ResponseEntity.ok(userResponseBody);
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestBody loginRequestBody){
        UserResponseBody userResponseBody = userService.loginUser(loginRequestBody);
        if( userResponseBody.email() == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("mensaje", "Credenciales no válidas"));
        }
        return ResponseEntity.ok(userResponseBody);
    }
    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser(){
        return null;
    }

}
