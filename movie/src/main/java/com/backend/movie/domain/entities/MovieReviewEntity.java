package com.backend.movie.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(
        name = "movie_review",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"reviewId", "userId", "movieId"})}
)
public class MovieReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "reviewId", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ReviewEntity review;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity author;
    @ManyToOne
    @JoinColumn(name = "movieId", referencedColumnName = "movieId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MovieEntity movie;
}
