package com.backend.movie.domain.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditReviewRequest {
    private UUID id;
    private String comment;
    private int rating;
    private boolean isAnonymous;
}
