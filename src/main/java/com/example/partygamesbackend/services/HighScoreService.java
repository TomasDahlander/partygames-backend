package com.example.partygamesbackend.services;

import com.example.partygamesbackend.models.HighScore;
import com.example.partygamesbackend.models.Question;
import com.example.partygamesbackend.repositories.HighScoreRepository;
import com.example.partygamesbackend.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
@RequiredArgsConstructor
public class HighScoreService {

    private final HighScoreRepository highScoreRepository;

    public List<HighScore> getAllHighScores() {
        return highScoreRepository.findAll();
    }

    public void addHighScore(int time, String date, String difficulty, String name) {
        HighScore highScore = HighScore.builder()
                .time(time)
                .date(LocalDate.parse(date))
                .difficulty(difficulty)
                .name(name)
                .build();
        highScoreRepository.save(highScore);
    }

    public String deleteHighScore(HighScore h){
        List<HighScore> list = highScoreRepository.findByTimeAndDateAndName(h.getTime(), h.getDate(), h.getName());
        highScoreRepository.deleteAll(list);
        return "Deleted: " + list;
    }
}
