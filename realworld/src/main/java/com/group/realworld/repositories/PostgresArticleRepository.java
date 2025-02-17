package com.group.realworld.repositories;

import com.group.realworld.models.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class PostgresArticleRepository implements ArticleRepository {
    private JdbcTemplate template;

    public PostgresArticleRepository(JdbcTemplate template) {
        this.template = template;
    }

    record ArticleDao(String uuid, String title, String description, String body, String authorId) {
    }

    record AuthorDao(String uuid, String username, String email) {
    }

    class ArticleRowMapper implements RowMapper<ArticleDao> {
        @Override
        public ArticleDao mapRow(ResultSet rs, int rowNum) throws SQLException {
            final var id = rs.getString("id");
            final var title = rs.getString("title");
            final var description = rs.getString("description");
            final var body = rs.getString("body");
            final var authorId = rs.getString("author_id");
            return new ArticleDao(id, title, description, body, authorId);
        }
    }

    class AuthorMapper implements RowMapper<AuthorDao> {

        @Override
        public AuthorDao mapRow(ResultSet rs, int rowNum) throws SQLException {
            final var id = rs.getString("id");
            final var username = rs.getString("username");
            final var email = rs.getString("email");
            return new AuthorDao(id, username, email);
        }
    }

    @Override
    public List<Article> getAllArticles() {
        final var sql = "select id, title, description, body, author_id from articles";
        final var articleDao = template.query(sql, new ArticleRowMapper());

        final var authorQuery = """
                select uuid, username , email
                from authors
                where id = ?""";
        final var authorDao = template.query(authorQuery, new AuthorMapper(), articleDao.authorId());

        return List.of();
    }

    @Override
    public Article createArticle(UUID uuid, String title, String body, String description, List<String> tags) {
        return null;
    }
}
