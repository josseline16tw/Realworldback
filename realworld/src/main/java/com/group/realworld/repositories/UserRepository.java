package com.group.realworld.repositories;

import com.group.realworld.models.User;

public interface UserRepository {
    User getCurrentUser();
    User getUserByEmail(String email);
    void saveUser(User usuario);
}
