package com.example.partygamesbackend.controllers;

import com.example.partygamesbackend.models.Question;
import com.example.partygamesbackend.repositories.QuestionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-05-29 <br>
 * Time: 17:50 <br>
 * Project: partygames-backend <br>
 */
@SpringBootTest
@AutoConfigureMockMvc
class QuestionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuestionRepository mockRepository;

    private List<Question> list;

    @BeforeEach
    public void reset(){
        list = Arrays.asList(
            new Question(1L,"Hur långt är ett rep?"),
            new Question(2L,"Vilken är din favoritfärg?"),
            new Question(3L,"Vad är snyggast?")
        );
        when(mockRepository.findById(1L)).thenReturn(Optional.ofNullable(list.get(0)));
        when(mockRepository.findById(2L)).thenReturn(Optional.ofNullable(list.get(1)));
        when(mockRepository.findById(3L)).thenReturn(Optional.ofNullable(list.get(2)));
        when(mockRepository.findAll()).thenReturn(list);
        when(mockRepository.save(list.get(0))).thenReturn(list.get(0));
        when(mockRepository.save(list.get(1))).thenReturn(list.get(1));
        when(mockRepository.save(list.get(2))).thenReturn(list.get(2));
        when(mockRepository.saveAll(list)).thenReturn(list);
    }


    @Test
    void getAllQuestions() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/question/get")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(3)));
    }

//    @Test
//    void addQuestion() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.post("/question/add")
//                .content(asJsonString(list.get(2)))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().is(200))
////                .andExpect(content().json("{\"id\":3,\"question\":\"Vad är snyggast?\"}"));
//                .andExpect(jsonPath("$.question", Matchers.containsStringIgnoringCase("Vad är snyggast?")));
//    }

    @Test
    void addQuestionList() {
    }

//    @Test
//    void deleteQuestionById() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.delete("/delete/by/id/2")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().is(200))
//                .andExpect(content().string(equalToIgnoringCase("Removed question with id number: 2")));
//    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}









/*
 @Test
    public void addCapitalTest() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/capital/add?name=Bangkok").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalToIgnoringCase("Saved a capital with name: Bangkok")));
    }

    @Test
    public void getCapitalById1Test() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/capital/get/id?id=1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Oslo\"}"));
    }

    @Test
    public void getAllCapitalTest() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/capital/get/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Oslo\"},{\"id\":2,\"name\":\"London\"},{\"id\":3,\"name\":\"Tokyo\"}]"));
    }
 */