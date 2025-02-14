package com.group.realworld.services;

import com.group.realworld.models.Article;
import com.group.realworld.repositories.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    public List<Article> getAllArticles() {
        return articleRepository.getAllArticles();
    }
}