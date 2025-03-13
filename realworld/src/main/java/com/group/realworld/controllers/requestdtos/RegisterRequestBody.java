package com.group.realworld.controllers.requestdtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequestBody (
        @NotBlank
        String username,
        @Email
        @NotBlank
        String email,
        @NotBlank
        String password
){
}
