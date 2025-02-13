package com.group.realworld.controllers;

// import modelos y servicios
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ArticleControllerTest {
    public static final String ARTICLES_ENDPOINT = "/api/articles";

    @Mock
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


        List<String> tagList = List.of("test");
        List<String> tagList_2 = List.of("TDD", "TDD2");

        UUID firstArticle = UUID.randomUUID();
        UUID secondArticle = UUID.randomUUID();

        Author author_1 = new Author (UUID.randomUUID(), "Test", "test2@test.com","", false);

        List<Article> articleList = List.of(
                new Article(firstArticle,"This is my title", "this-is-my-title", "This is about something", "Test test test test", author_1, createdAt, updateAt, false , 1, tagList),
                new Article(secondArticle,"Test -1", "test-1", "Test -123", "test test test test", author_1, createdAt, updateAt, false , 0, tagList)
                );

        when(articleService.getAllArticles()).thenReturn(articleList);
        int expectedNumArticles = 2;

        ResponseEntity<ArticlesResponseBody> resultGetArticles = articleController.getArticles();
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

}
