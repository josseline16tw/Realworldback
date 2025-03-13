package com.group.realworld.repositories.dao;

public record UserDao(
        String uuid,
        String username,
        String email,
        String password,
        String bio,
        String image
) {
}
