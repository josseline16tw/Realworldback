package com.group.realworld.models;

import com.group.realworld.models.Author;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Article {
    private UUID uuid;
    private String title;
    private String slug;
    private String description;
    private String body;
    private Author author;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private boolean favorited;
    private int favoritesCount;
    private List<String> tagList;

    public Article() {
        this(null, null, null, null, null);
    }

    public Article(UUID uuid, String title, String description, String body, Author author) {
        this(uuid, title, null, description, body, author, LocalDate.now(), LocalDate.now(), false, 0, List.of());
    }

    public Article(UUID uuid, String title, String slug, String description, String body, Author author, LocalDate createdAt, LocalDate updatedAt, boolean favorited, int favoritesCount, List<String> tagList) {
        this.uuid = uuid;
        this.title = title;
        this.slug = slug;
        this.description = description;
        this.body = body;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.favorited = favorited;
        this.favoritesCount = favoritesCount;
        this.tagList = tagList;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public int getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(int favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}