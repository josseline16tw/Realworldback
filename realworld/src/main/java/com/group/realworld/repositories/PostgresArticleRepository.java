package com.group.realworld.repositories;

import com.group.realworld.models.Article;
import com.group.realworld.models.Author;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Repository("postgres")
public class PostgresArticleRepository implements ArticleRepository {
    private JdbcTemplate template;

    public PostgresArticleRepository(JdbcTemplate template) {
        this.template = template;
    }

    record ArticleDao(String uuid, String title, String description, String body, AuthorDao author, String tags) {
    }

    record AuthorDao(String uuid, String username, String email) {
    }

    static class ArticleRowMapper implements RowMapper<ArticleDao> {
        @Override
        public ArticleDao mapRow(ResultSet rs, int rowNum) throws SQLException {
            final var id = rs.getString("id");
            final var title = rs.getString("title");
            final var description = rs.getString("description");
            final var body = rs.getString("body");
            final var authorId = rs.getString("author_id");
            final var username = rs.getString("username");
            final var email = rs.getString("email");
            final var authorDao = new AuthorDao(authorId, username, email);
            final var tags = rs.getString("tags");
            return new ArticleDao(id, title, description, body, authorDao, tags);
        }
    }

    @Override
    public List<Article> getAllArticles() {
        final var sql = """
                select ar.id, title, description, body, author_id, username, email, string_agg(t.name, ',') as tags
                  from articles ar
                    inner join authors au
                       on ar.author_id = au.id
                    left join articles_tags at
                       on ar.id = at.article_id
                    inner join tags t
                       on at.tag_id = t.id
                  group by ar.id, title, description, body, author_id, username, email
                """;
        final var articleDaos = template.query(sql, new ArticleRowMapper());
        final var articles = articleDaos
                .stream()
                .map(dao -> {
                    final var article = new Article(
                            UUID.fromString(dao.uuid), dao.title, dao.description, dao.body,
                            new Author(UUID.fromString(dao.author.uuid), dao.author.username, dao.author.email));
                    article.setTagList(Arrays.stream(dao.tags.split(",")).toList());
                    return article;
                })
                .toList();
        return articles;
    }

    @Override
    public Article createArticle(UUID uuid, String title, String body, String description, List<String> tags) {
        UUID authorId = UUID.fromString("c14c0bd1-1604-430c-93f9-7bab6a6e3c56");
        final var sql = """
                insert into articles (id, title, description, body, author_id)
                values (?, ?, ?, ?, ?)
                """;
        var result = template.update(sql, uuid,title,description,body,authorId);
        if(!tags.isEmpty()){
            var insertedTags = insertTags(tags);
            createTagsArticle(insertedTags,uuid);
        }
        final var sqlGetArticle = """
                select ar.id, title, description, body, author_id, username, email, string_agg(t.name, ',') as tags
                  from articles ar
                    inner join authors au
                       on ar.author_id = au.id
                    left join articles_tags at
                       on ar.id = at.article_id
                    inner join tags t
                       on at.tag_id = t.id
                  where ar.id = "%s"
                  group by ar.id, title, description, body, author_id, username, email
                """
                .formatted(uuid.toString());

        final var dao = template.queryForObject(sql, new ArticleRowMapper());

        final var article = new Article(
                UUID.fromString(dao.uuid), dao.title, dao.description, dao.body,
                new Author(UUID.fromString(dao.author.uuid), dao.author.username, dao.author.email));
        article.setTagList(Arrays.stream(dao.tags.split(",")).toList());

        return article;
    }

    public List<UUID> insertTags(List<String> tags) {
        List<UUID> tagsIds = new ArrayList<>();
        String sql = """
                INSERT INTO tags (id, name)
                VALUES (?, ?)
                ON CONFLICT (name) DO UPDATE SET name = EXCLUDED.name
                RETURNING id;
                """;
        for (String tag : tags){
            UUID uuid= UUID.randomUUID();
            UUID uuidReturn = template.queryForObject(sql,UUID.class,uuid,tag);
            tagsIds.add(uuidReturn);
        }
        return tagsIds;
    }

    public void createTagsArticle (List<UUID> tags, UUID articleId){
        String sql = """
                INSERT INTO articles_tags (article_id, tag_id)
                VALUES (?, ?)
                """;
        List<Object[]> batchArgs = tags.stream()
                .map(tagId -> new Object[]{articleId, tagId})
                .toList(); /// TODO Preguntar cual seria la mejor solucion

        template.batchUpdate(sql, batchArgs);
    }

}
