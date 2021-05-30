package com.example.partygamesbackend.controllers;

import com.example.partygamesbackend.models.Question;
import com.example.partygamesbackend.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-05-29 <br>
 * Time: 14:29 <br>
 * Project: partygames-backend <br>
 */
@RestController
@RequestMapping("/question")
@CrossOrigin
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String info(){
        return "/get = Get all questions";
    }

    @GetMapping("/get")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @PostMapping("/add")
    public Question addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PostMapping("/add/list")
    public List<Question> addQuestionList(@RequestBody List<Question> questions){
        return questionService.addQuestionList(questions);
    }

    @DeleteMapping("/delete/by/id/{id}")
    public String deleteQuestionById(@PathVariable Long id){
        return questionService.deleteQuestionById(id);
    }
}

