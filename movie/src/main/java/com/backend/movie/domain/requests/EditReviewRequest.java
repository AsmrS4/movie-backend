package com.backend.movie.domain.requests;

import com.backend.movie.domain.models.UserShort;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
