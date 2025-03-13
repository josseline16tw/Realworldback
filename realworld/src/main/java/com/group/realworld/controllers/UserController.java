package com.group.realworld.controllers;

import com.group.realworld.controllers.requestdtos.RegisterRequestBody;
import com.group.realworld.controllers.responsedtos.UserResponseBody;
import com.group.realworld.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseBody> registerUser(@RequestBody RegisterRequestBody requestBody){
        UserResponseBody userResponseBody = userService.registerUser(requestBody);
        return ResponseEntity.ok(userResponseBody);
    }
}
