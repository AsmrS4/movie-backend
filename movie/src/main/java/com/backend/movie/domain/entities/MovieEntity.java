package com.backend.movie.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@ToString(exclude = "genres")
@Table(name = "movies")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID movieId;
    private String title;
    private String description;
    private int filmYear;
    private String country;
    @Column(length = 2000)
    private String imageUrl;
    private int lasting;
    private double rating = 0;
    private int ageLimit;
    private String budget;
    private String fees;
    private String actors;
    private String director;

    @ManyToMany
    @JoinTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<GenreEntity> genres;
    @OneToMany(mappedBy="movie", cascade={CascadeType.ALL}, orphanRemoval=true)
    private Set<Favourites> favorites;
}
