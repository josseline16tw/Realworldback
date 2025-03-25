package com.group.realworld.config;

import com.group.realworld.models.User;
import com.group.realworld.repositories.PostgresUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final PostgresUserRepository postgresUserRepository;

    public UserDetailsServiceImpl(PostgresUserRepository postgresUserRepository) {
        this.postgresUserRepository = postgresUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = postgresUserRepository.getUserByEmail(email);

        if (user != null){
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.emptyList());
        }

        return null;
    }
}
