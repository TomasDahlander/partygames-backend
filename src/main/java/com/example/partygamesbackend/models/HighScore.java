package com.example.partygamesbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-05-29 <br>
 * Time: 14:26 <br>
 * Project: partygames-backend <br>
 */
@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HighScore implements Serializable {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    private int time;
    private LocalDate date;
    private String difficulty;
    private String name;
}

