package com.example.partygamesbackend.controllers;

import com.example.partygamesbackend.models.HighScore;
import com.example.partygamesbackend.models.Question;
import com.example.partygamesbackend.services.HighScoreService;
import com.example.partygamesbackend.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-05-29 <br>
 * Time: 14:29 <br>
 * Project: partygames-backend <br>
 */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/highscore")
public class HighScoreController {

    private final HighScoreService highScoreService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<HighScore> getAllHighScores(){
        return highScoreService.getAllHighScores();
    }

    @GetMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addHighScore(
            @RequestParam int time,
            @RequestParam String date,
            @RequestParam String difficulty,
            @RequestParam String name){
        System.out.println("HighScoreController:addHighScore - Adding highscore");
        return highScoreService.addHighScore(time,date,difficulty,name);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public String deleteHighScore(){
        System.out.println("HighScoreController:deleteHighScore - Deleting all highscore entries");
        return highScoreService.deleteHighScore();
    }
}


