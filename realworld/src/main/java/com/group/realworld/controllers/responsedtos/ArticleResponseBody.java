package com.group.realworld.controllers.responsedtos;

import com.group.realworld.models.Author;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ArticleResponseBody(
        String id,
        String title,
        String slug,
        String description,
        String body,
        Author author,
        LocalDate createdAt,
        LocalDate updatedAt,
        boolean favorited,
        int favoritesCount,
        List<String>tagList
) {

}
