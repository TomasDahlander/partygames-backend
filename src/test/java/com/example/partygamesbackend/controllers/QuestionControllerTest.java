package com.example.partygamesbackend.controllers;

import com.example.partygamesbackend.models.Question;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-05-29 <br>
 * Time: 17:50 <br>
 * Project: partygames-backend <br>
 */
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
class QuestionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void infoTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/question/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    void getAllQuestionsTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/question/get")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    void getLatestUpdatedQuestionsTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/question/get/latest/update")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    void addQuestionTest() throws Exception {
        Question question = new Question(1L,"Detta är en testfråga");

        mvc.perform(MockMvcRequestBuilders.post("/question/add")
                .content(asJsonString(question))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    void addQuestionListTest() throws Exception {
        List<Question> list = Arrays.asList(
                new Question(1L,"Fråga 1"),
                new Question(2L,"Fråga 2")
        );

        mvc.perform(MockMvcRequestBuilders.post("/question/add/list")
                .content(asJsonString(list))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    void updateQuestionTest() throws Exception {
        Question question = new Question(1L,"Detta är en testfråga");

        mvc.perform(MockMvcRequestBuilders.put("/question/update")
                .content(asJsonString(question))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    void deleteQuestionByIdTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/question/delete/by/id/2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    void deleteQuestionsBetweenIdsTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/question/delete/ids/1/2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
