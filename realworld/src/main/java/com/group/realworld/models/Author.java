package com.group.realworld.models;

import java.util.UUID;

public class Author {
    private UUID uuid;
    private String username;
    private String email;
    private String bio;
    private String image;
    private boolean following;

    public Author() {
    }

    public Author(UUID uuid, String username, String email) {
        this.uuid = uuid;
        this.username = username;
        this.email = email;
    }

    public Author(UUID uuid, String username, String email, String bio, boolean following) {
        this.uuid = uuid;
        this.email = email;
        this.username = username;
        this.bio = bio;
        this.image = null;
        this.following = following;
    }

    public Author(UUID uuid, String username, String email, String bio, String image, boolean following) {
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.image = image;
        this.following = following;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getBio() {
        return bio;
    }

    public String getImage() {
        return image;
    }

    public boolean isFollowing() {
        return following;
    }
}