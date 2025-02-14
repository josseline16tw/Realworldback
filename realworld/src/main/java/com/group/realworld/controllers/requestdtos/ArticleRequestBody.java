package com.group.realworld.controllers.requestdtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record ArticleRequestBody(
        @NotBlank(message = "Title is required")
        String title,
        @NotBlank(message = "Description is required")
        String description,
        @NotBlank(message = "Body is required")
        String body,
        @Size(min = 1, message = "At least one tag")
        List<String> tags
) {
}
