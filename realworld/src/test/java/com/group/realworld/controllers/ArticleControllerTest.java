package com.group.realworld.controllers;

// import modelos y servicios
import com.group.realworld.controllers.requestdtos.ArticleRequestBody;
import com.group.realworld.controllers.responsedtos.ArticleResponseBody;
import com.group.realworld.controllers.responsedtos.ArticlesResponseBody;
import com.group.realworld.models.Article;
import com.group.realworld.models.Author;
import com.group.realworld.services.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.time.LocalDate;
import java.util.List;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ArticleControllerTest {
    public static final String ARTICLES_ENDPOINT = "/api/articles";
    public static final String CREATE_ARTICLE_REQUEST_BODY = """
            {
              "article": {
                "title": "%s",
                "description": "%s",
                "body": "%s",
                "tagList": ["%s"]
              }
            }
            """;
    private final String title = "This is my title";
    private final String description = "This is about something";
    private final String body = "Test test test test";
    private final List<String> tagList = List.of("test");

    private ArticleService articleService;

    ArticleController articleController;

    public ArticleControllerTest() {
        articleService = mock(ArticleService.class);
        articleController = new ArticleController(articleService);
    }

    @Test
    public void shouldReturnAllArticleList() throws Exception {
        LocalDate createdAt = LocalDate.parse("2025-01-21");
        LocalDate updateAt = LocalDate.parse("2025-01-21");

        UUID firstArticle = UUID.randomUUID();
        UUID secondArticle = UUID.randomUUID();

        Author author_1 = new Author (UUID.randomUUID(), "Test", "test2@test.com","", false);

        List<Article> articleList = List.of(
                new Article(firstArticle,this.title, "this-is-my-title", this.description, this.body, author_1, createdAt, updateAt, false , 1, this.tagList),
                new Article(secondArticle,"Test -1", "test-1", "Test -123", "test test test test", author_1, createdAt, updateAt, false , 0, this.tagList)
                );

        when(articleService.getAllArticles(null, null, null)).thenReturn(articleList);
        int expectedNumArticles = 2;

        ResponseEntity<ArticlesResponseBody> resultGetArticles = articleController.getArticles(null, null, null);
        assertNotNull(resultGetArticles);
        int resultNumArticles = resultGetArticles.getBody().articles().size();

        assertEquals(expectedNumArticles, resultNumArticles);

        String expectedResponse = """
                    {
                         "articles": [
                             {
                                 "title": "This is my title",
                                 "slug": "this-is-my-title",
                                 "description": "This is about something",
                                 "body": "Test test test test",
                                 "author": {
                                     "username": "Test",
                                     "email": "test2@test.com",
                                     "bio": "",
                                     "image": null,
                                     "following": false
                                 },
                                 "createdAt": "2025-01-21",
                                 "updatedAt": "2025-01-21",
                                 "favorited": false,
                                 "favoritesCount": 1,
                                 "tagList": [
                                     "test"
                                 ]
                             },
                             {
                                 "title": "Test -1",
                                 "slug": "test-1",
                                 "description": "Test -123",
                                 "body": "test test test test",
                                 "author": {
                                     "username": "Test",
                                     "email": "test2@test.com",
                                     "bio": "",
                                     "image": null,
                                     "following": false
                                 },
                                 "createdAt": "2025-01-21",
                                 "updatedAt": "2025-01-21",
                                 "favorited": false,
                                 "favoritesCount": 0,
                                 "tagList": [
                                     "test"
                                 ]
                             },
                            ],
                            "articlesCount": 26
                          }
                """;


    }

    @Test
    public void shouldCreateArticle() throws Exception {
        //String createArticleRequestJson = String.format(CREATE_ARTICLE_REQUEST_BODY, this.title, this.description, this.body, this.tagList);
        Article article = new Article(UUID.randomUUID(),this.title, null, this.description, this.body, null, null, null, false , 0, this.tagList);
        when(articleService.createArticle(eq(this.title), eq(this.description), eq(this.body), eq(this.tagList))).thenReturn(article);
        ArticleRequestBody articleRequestBody = new ArticleRequestBody(this.title, this.description, this.body, this.tagList);
        //cuando se testea el body createArticleRequestJson
        ResponseEntity<ArticleResponseBody> resultGetArticle = articleController.createArticle(articleRequestBody);
        assertNotNull(resultGetArticle);
        String articleTitle = resultGetArticle.getBody().title();

        assertEquals(this.title, articleTitle);
        verify(articleService).createArticle(this.title, this.description, this.body, this.tagList);
    }
}
