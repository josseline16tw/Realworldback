package com.group.realworld.controllers;

// import modelos y servicios
import com.group.realworld.controllers.ArticleController;
import com.group.realworld.models.Article;
import com.group.realworld.models.Author;
import com.group.realworld.services.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;
import java.util.List;

import java.util.UUID;


import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ArticleController.class)

class ArticleControllerTest {
    public static final String ARTICLES_ENDPOINT = "/api/articles";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;
/*
    @Test
    public void shouldCreateRsvp() throws Exception {
        String createEventRequestJson = String.format(RSVP_REQUEST_BODY, eventId, attendeeName, selectedDate);
        when(rsvpService.createRsvp(eq(eventId), eq(attendeeName), eq(selectedDates))).thenReturn(rsvp);

        mockMvc.perform(
                        post(RSVPS_ENDPOINT)
                                .content(createEventRequestJson)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andExpect(header().string("location", "rsvp/" + rsvpId));
    }

    @Test
    public void shouldReturnBadRequestIfRequestBodyIsNotValid() throws Exception {
        String invalidRsvpRequestJson = String.format(RSVP_REQUEST_BODY, eventId, "", selectedDate);

        mockMvc.perform(
                        post(RSVPS_ENDPOINT)
                                .content(invalidRsvpRequestJson)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Bad Request")))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.message", is(not(emptyString()))));
    }

    @Test
    public void shouldGetRsvpByIdAndIncludeAssociatedEventData() throws Exception {
        LocalDate firstDate = LocalDate.parse("2030-01-09");
        LocalDate secondDate = LocalDate.parse("2030-01-15");
        LocalDate thirdDate = LocalDate.parse("2030-02-02");

        List<LocalDate> proposedDates = List.of(firstDate, secondDate, thirdDate);
        List<LocalDate> selectedDates = List.of(firstDate, secondDate);

        Rsvp rsvp = new Rsvp(rsvpId, eventId, "John Doe", selectedDates);

        Event event = new Event(eventId, "Event Name", "Description", proposedDates,
                "Event Organizer", OPENED.getStatus(), null);

        when(rsvpService.getRsvpByUuid(rsvpId)).thenReturn(Optional.of(rsvp));
        when(eventService.getEventByUuid(eventId)).thenReturn(Optional.of(event));

        String expectedResponse = """
                    {
                        "event": {
                          "name": "Event Name",
                          "description": "Description",
                          "proposedDates": ["2030-01-09", "2030-01-15", "2030-02-02"],
                          "organizerName": "Event Organizer"
                        },
                        "rsvp": {
                          "attendeeName": "John Doe",
                          "selectedDates": ["2030-01-09", "2030-01-15"]
                        }
                    }
                """;

        mockMvc.perform(
                        get(RSVPS_ENDPOINT + "/" + rsvpId)
                ).andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    public void shouldReturnNotFoundIfGetRsvpByIdDoesNotExist() throws Exception {
        when(rsvpService.getRsvpByUuid(rsvpId)).thenReturn(Optional.empty());

        mockMvc.perform(
                get(RSVPS_ENDPOINT + "/" + rsvpId)
        ).andExpect(status().isNotFound());
    }*/

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

        mockMvc.perform(
                        get(ARTICLES_ENDPOINT)
                ).andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }
/*
    @Test
    public void shouldReturnNotFoundIfEventDoesNotExist() throws Exception {
        when(eventService.getEventByUuid(eventId)).thenReturn(Optional.empty());

        mockMvc.perform(
                get(RSVPS_ENDPOINT + "/event/" + eventId)
        ).andExpect(status().isNotFound());
    }*/
}
