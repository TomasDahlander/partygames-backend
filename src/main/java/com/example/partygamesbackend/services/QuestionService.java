package com.example.partygamesbackend.services;

import com.example.partygamesbackend.models.Question;
import com.example.partygamesbackend.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-05-29 <br>
 * Time: 14:28 <br>
 * Project: partygames-backend <br>
 */
@RequiredArgsConstructor
@Service
public class QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> addQuestionList(List<Question> questions) {
        return questionRepository.saveAll(questions);
    }

    public String deleteQuestionById(Long id) {
        Question question = questionRepository.findById(id).orElse(null);
        if(question == null){
            return "Question did not exist in database.";
        }
        else{
            questionRepository.deleteById(id);
            return "Removed question with id number: " + id;
        }
    }
}
