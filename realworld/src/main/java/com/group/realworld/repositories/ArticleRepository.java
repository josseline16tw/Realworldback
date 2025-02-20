package com.group.realworld.repositories;

import com.group.realworld.models.Article;

import java.util.List;
import java.util.UUID;

/**
 *
 */
public interface ArticleRepository {

    /**
     * @return
     */
    List<Article> getAllArticles();

    /**
     * @param uuid
     * @param title
     * @param body
     * @param description
     * @param tags
     * @return
     */

    Article createArticle(UUID uuid, String title, String body, String description, List<String> tags);

}
