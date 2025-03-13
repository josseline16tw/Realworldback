package com.group.realworld.models;

import java.util.UUID;

public class User {
    private UUID uuid;
    private String username;
    private String email;
    private String password;
    private String bio;
    private String image;

    public User(UUID uuid, String username, String email, String password, String bio, String image) {
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.image = image;
    }

    public User(UUID uuid, String username, String email, String password) {
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
