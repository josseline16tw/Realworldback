package com.group.realworld.services;

import com.group.realworld.models.Article;
import com.group.realworld.repositories.ArticleRepository;
import com.group.realworld.repositories.InMemoryArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.getAllArticles();
    }

    // TODO: Update createArticle to receive article author.
    public Article createArticle(String title, String body, String description, List<String> tags) {
        UUID generatedUuid = UUID.randomUUID();
        return articleRepository.createArticle(generatedUuid, title, body, description, tags);
    }
}