package com.example.partygamesbackend.repositories;

import com.example.partygamesbackend.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-05-29 <br>
 * Time: 14:27 <br>
 * Project: partygames-backend <br>
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
