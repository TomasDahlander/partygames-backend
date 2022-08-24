package com.example.partygamesbackend.services;

import com.example.partygamesbackend.models.Question;
import com.example.partygamesbackend.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-05-29 <br>
 * Time: 14:28 <br>
 * Project: partygames-backend <br>
 */
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

    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<String> deleteQuestionsBetweenIds(Long from, Long to) {
        List<String> list = new ArrayList<>();
        list.add("Deleted questions by id:");

        Long counter = from;
        while(counter <= to){

            Question question = questionRepository.findById(counter).orElse(null);
            if(question != null){
                list.add(String.valueOf(counter));
                questionRepository.deleteById(counter);
            }
            counter++;
        }
        return list;
    }

    public List<String> getLatestUpdatedQuestions() {
        List<Question> list = questionRepository.findAll();
        return list.stream().map(Question::getQuestion).collect(Collectors.toList());
    }
}
