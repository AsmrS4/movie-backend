package com.backend.movie.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private UUID movieId;
    private String title;
    private String description;
    private int filmYear;
    private String country;
    private String imageUrl;
    private int lasting;
    private double rating;
    private int ageLimit;
    private String budget;
    private String fees;
    private String actors;
    private String director;
    private List<Genre> genres;
}
