package com.backend.movie.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID movieId;
    private String title;
    private String description;
    private int year;
    private String country;
    private String imageUrl;
    private int time;
    private int ageLimit;
    private int budget;
    private int fees;
    private String actors;
    private String director;
}
