package com.group.realworld.repositories;

import com.group.realworld.models.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("memory")
public class InMemoryArticleRepository implements ArticleRepository {
    private final List<Article> articleList = new ArrayList<Article>();

    public List<Article> getAllArticles() {
        return this.articleList;
    }

    public Article createArticle(UUID uuid, String title, String body, String description, List<String> tags) {
        Article article = new Article(uuid, title, null, description, body, null, null, null, false, 0, tags);
        this.articleList.add(article);
        return article;
    }

}
