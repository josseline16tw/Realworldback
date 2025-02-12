package com.group.realworld.services;

import com.group.realworld.models.Article;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();
        return articles;
    }
}