package com.group.realworld.controllers.requestdtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestBody(
        @Email
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
