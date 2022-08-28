package com.example.partygamesbackend.services;

import com.example.partygamesbackend.models.HighScore;
import com.example.partygamesbackend.repositories.HighScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public String addHighScore(int time, String date, String difficulty, String name) {
        HighScore highScore = HighScore.builder()
                .time(time)
                .date(LocalDate.parse(date))
                .difficulty(difficulty)
                .name(name)
                .build();
        highScore = highScoreRepository.save(highScore);
        System.out.println("HighScoreService:addHighScore - Added highscore: " + highScore);

        deleteBelowThe50Best(difficulty);

        return "Added Highscore: " + highScore;
    }

    public String deleteHighScore(){
        highScoreRepository.deleteAll();
        return "Deleted all highscore entries. Beginning new clean slate.";
    }

    private void deleteBelowThe50Best(String difficulty){
        List<HighScore> highScores = highScoreRepository.findByDifficultyOrderByTimeDesc(difficulty);
        System.out.println("HighScoreService:deleteBelowThe50Best - Amount of " + difficulty + " highscores: " + highScores.size());
        while(highScores.size() > 50){
            HighScore highScore = highScores.remove(0);
            highScoreRepository.delete(highScore);
            System.out.println("HighScoreService:deleteBelowThe50Best - Deleted Highscore: " + highScore);
        }
        System.out.println("HighScoreService:deleteBelowThe50Best - Amount of " + difficulty + " highscore are now: " + highScores.size());
    }
}
