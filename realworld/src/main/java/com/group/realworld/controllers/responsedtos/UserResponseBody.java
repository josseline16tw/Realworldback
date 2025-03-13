package com.group.realworld.controllers.responsedtos;

public record UserResponseBody(
        String username,
        String email,
        String bio,
        String image,
        String token // TODO PREGUNTAR SI VA ESTO AQUI
) {
}
