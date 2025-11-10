package com.backend.movie.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private UUID id;
    private String comment;
    private int rating;
    private LocalDateTime createTime;
    private UserShort author;
    private boolean isAnonymous;
}

