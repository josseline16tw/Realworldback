package com.group.realworld.repositories;

import com.group.realworld.models.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepository {
    private final List<Article> articleList = new ArrayList<Article>();

    public List<Article> getAllArticles() {
        return this.articleList;
    }



}
