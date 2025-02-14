package com.group.realworld.Services;

import com.group.realworld.models.Article;
import com.group.realworld.models.Author;
import com.group.realworld.repositories.ArticleRepository;
import com.group.realworld.services.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArticleServiceTest {

    //@Mock
    private ArticleRepository articleRepository;

    //@InjectMocks
    private ArticleService articleService;


    @BeforeEach
    public void setUp() {
        articleRepository = mock(ArticleRepository.class);
        articleService = new ArticleService(articleRepository);
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
        when(articleRepository.getAllArticles()).thenReturn(articleList);

        List<Article> resultAllArticles = articleService.getAllArticles();

        assertEquals(2, resultAllArticles.size());
        assertEquals(articleList.get(0).getUuid(), resultAllArticles.get(0).getUuid());
    }
}
