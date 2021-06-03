package com.example.partygamesbackend.services;

import com.example.partygamesbackend.models.Question;
import com.example.partygamesbackend.repositories.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-05-30 <br>
 * Time: 11:36 <br>
 * Project: partygames-backend <br>
 */
@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    QuestionService questionService;

    @Mock
    QuestionRepository mockRepository;

    List<Question> list;

    @BeforeEach
    public void reset() {
        list = Arrays.asList(
                new Question(1L, "Hur långt är ett rep?"),
                new Question(2L, "Vilken är din favoritfärg?"),
                new Question(3L, "Vad är snyggast?")
        );
        questionService = new QuestionService(mockRepository);
    }

    @Test
    void getAllQuestionsTest() {
        when(mockRepository.findAll()).thenReturn(list);

        List<Question> actual = questionService.getAllQuestions();

        assertEquals(actual, list);
        assertNotEquals(actual, null);
        verify(mockRepository).findAll();
    }

    @Test
    void addQuestionTest() {
        when(mockRepository.save(list.get(0))).thenReturn(list.get(0));

        Question actual = questionService.addQuestion(list.get(0));

        assertEquals(actual, list.get(0));
        assertNotEquals(actual, null);
        assertNotEquals(actual, list.get(1));
        verify(mockRepository).save(any());
    }

    @Test
    void addQuestionListTest() {
        when(mockRepository.saveAll(list)).thenReturn(list);

        List<Question> actual = questionService.addQuestionList(list);

        assertEquals(actual, list);
        assertNotEquals(actual, null);
        verify(mockRepository).saveAll(any());
    }

    @Test
    void updateQuestionTest() {
        Question newQuestion = new Question(1L, "Ny fråga");

        when(mockRepository.save(any())).thenReturn(newQuestion);

        Question actual = questionService.updateQuestion(list.get(0));

        assertEquals(actual, newQuestion);

        verify(mockRepository).save(any());
    }

    @Test
    void deleteQuestionByIdTest() {
        when(mockRepository.findById(1L)).thenReturn(Optional.ofNullable(list.get(0)));

        String actual = questionService.deleteQuestionById(1L);

        assertEquals(actual, "Removed question with id number: 1");
        assertNotEquals(actual, "Removed question with id number: 2");

        verify(mockRepository).findById(1L);
        verify(mockRepository).deleteById(1L);

        when(mockRepository.findById(4L)).thenReturn(Optional.empty());

        String actualEmpty = questionService.deleteQuestionById(4L);

        assertEquals(actualEmpty, "Question did not exist in database.");
        assertNotEquals(actualEmpty, null);

        verify(mockRepository).findById(4L);
    }

    @Test
    void deleteQuestionsBetweenIdsTest(){
        when(mockRepository.findById(1L)).thenReturn(Optional.ofNullable(list.get(0)));
        when(mockRepository.findById(2L)).thenReturn(Optional.ofNullable(list.get(1)));

        List<String> expected = Arrays.asList("Deleted questions by id:","1","2");

        List<String> actual = questionService.deleteQuestionsBetweenIds(1L,2L);

        assertEquals(actual,expected);

        verify(mockRepository).findById(1L);
        verify(mockRepository).findById(2L);
    }
}