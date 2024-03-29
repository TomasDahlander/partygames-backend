package com.example.partygamesbackend.controllers;

import com.example.partygamesbackend.models.Question;
import com.example.partygamesbackend.services.QuestionService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/")
    public String info(){
        return "/get = Get all questions";
    }

    @GetMapping("/get")
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/get/latest/update")
    public List<String> getLatestUpdatedQuestions(){
        return questionService.getLatestUpdatedQuestions();
    }

    @PostMapping("/add")
    public Question addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @PostMapping("/add/list")
    public List<Question> addQuestionList(@RequestBody List<Question> questions){
        return questionService.addQuestionList(questions);
    }

    @PutMapping("/update")
    public Question updateQuestion(@RequestBody Question question){
        return questionService.updateQuestion(question);
    }

    @DeleteMapping("/delete/by/id/{id}")
    public String deleteQuestionById(@PathVariable Long id){
        return questionService.deleteQuestionById(id);
    }

    @DeleteMapping("/delete/ids/{from}/{to}")
    public List<String> deleteQuestionsBetweenIds(@PathVariable Long from, @PathVariable Long to){
        return questionService.deleteQuestionsBetweenIds(from,to);
    }
}

