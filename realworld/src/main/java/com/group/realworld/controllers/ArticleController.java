package com.group.realworld.controllers;
import com.group.realworld.controllers.responsedtos.ArticleResponseBody;
import com.group.realworld.controllers.responsedtos.ArticlesResponseBody;
import com.group.realworld.models.Article;
import com.group.realworld.services.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }
    @GetMapping
    public ResponseEntity<ArticlesResponseBody> getArticles() {
        List<Article> articleList = articleService.getAllArticles();
        List<ArticleResponseBody> articleResponseBodyList = articleList.stream().map( article ->
                new ArticleResponseBody(
                        article.getUuid().toString(),
                        article.getTitle(),
                        article.getSlug(),
                        article.getDescription(),
                        article.getBody(),
                        article.getAuthor(),
                        article.getCreatedAt(),
                        article.getUpdatedAt(),
                        article.isFavorited(),
                        article.getFavoritesCount(),
                        article.getTagList()
                )
                ).collect(Collectors.toList());
        return ResponseEntity.ok(new ArticlesResponseBody(articleResponseBodyList));
    }
}