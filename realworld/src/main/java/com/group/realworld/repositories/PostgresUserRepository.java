package com.group.realworld.repositories;

import com.group.realworld.models.User;
import com.group.realworld.repositories.dao.UserDao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.net.SocketTimeoutException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Repository("postgresUserRepo")
public class PostgresUserRepository implements UserRepository {

    private JdbcTemplate template;

    public PostgresUserRepository(JdbcTemplate template) {
        this.template = template;
    }

    static class UserRowMapper implements RowMapper<UserDao>{
        public UserDao mapRow(ResultSet rs, int rowNum) throws SQLException {
            final var id = rs.getString("id");
            final var username = rs.getString("username");
            final var email = rs.getString("email");
            final var password = rs.getString("password");
            final var bio = rs.getString("bio");
            final var image = rs.getString("image");
            return new UserDao(id,username,email,password,bio,image);
        }
    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        System.out.println("antes query");
        System.out.println(email);

        try {
            final var sql = """
            SELECT id, email, password, username, bio, image
            FROM users WHERE email = ?
            """;
            final var dao = template.queryForObject(sql, new UserRowMapper(), email);

            System.out.println("query de funcion");
            System.out.println(dao);
            User user = new User(UUID.fromString(dao.uuid()), dao.username(), dao.email(), dao.password(), dao.bio(), dao.image());
            return user;
        } catch (EmptyResultDataAccessException e) {
            System.out.println("No se encontró el usuario con el ID especificado.");
        }

        return null;
    }

    @Override
    public void saveUser(User user) {
        final var sql = """
            INSERT INTO users (id, username, email, password)
            VALUES (?, ?, ?, ?)
            """;
        template.update(sql, user.getUuid(), user.getUsername(), user.getEmail(), user.getPassword());
        System.out.println("despues de save user");
    }
}
