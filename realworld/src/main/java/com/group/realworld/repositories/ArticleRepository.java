package com.group.realworld.repositories;

import com.group.realworld.models.Article;

import java.util.List;
import java.util.UUID;

/**
 *
 */
public interface ArticleRepository {

    /**
     * @param tag
     * @return
     */
    List<Article> getAllArticles(String tag, String limit, String offset);

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
