package com.backend.movie.controllers;


import com.backend.movie.domain.requests.EditReviewRequest;
import com.backend.movie.domain.requests.ReviewRequest;
import com.backend.movie.services.reviews.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Review", description = "Эндпоинты для создания, редактирования и удаления отзывов к фильмам.")
public class ReviewController {
    private final ReviewService reviewService;
    public ReviewController(@Autowired ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @Operation(
            summary = "Получить отзывы к фильму",
            description = "В ответе возвращается список отзывов"
    )
    @GetMapping("/{movieId}")
    public ResponseEntity<?> getReviews(@PathVariable UUID movieId) {
        return ResponseEntity.ok(reviewService.getReviews(movieId));
    }
    @Operation(
            summary = "Написать отзыв к фильму",
            description = "В ответе возвращается новый отзыв пользователя."
    )
    @PostMapping("/{movieId}")
    public ResponseEntity<?> createReview(@PathVariable UUID movieId, @RequestBody ReviewRequest request) throws BadRequestException {
        return ResponseEntity.ok(reviewService.createReview(movieId, request));
    }
    @Operation(
            summary = "Изменить написанный отзыв",
            description = "В ответе возвращается измененный отзыв."
    )
    @PutMapping("/{movieId}")
    public ResponseEntity<?> editReview(@PathVariable UUID movieId, @RequestBody EditReviewRequest request) {
        return ResponseEntity.ok(reviewService.editReview(request));
    }
    @Operation(
            summary = "Удалить отзыв",
            description = "В ответе возвращается статус с кодом операции."
    )
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable UUID reviewId) {
        return ResponseEntity.ok(reviewService.deleteReview(reviewId));
    }
}
