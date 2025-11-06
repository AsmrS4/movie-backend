package com.backend.movie.domain.models;

import lombok.Data;

import java.util.UUID;

@Data
public class Movie {
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
