package com.backend.movie.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "reviews")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String comment;
    private int rating;
    private LocalDateTime createTime = LocalDateTime.now();

}
