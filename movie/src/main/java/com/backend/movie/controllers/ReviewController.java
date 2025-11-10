package com.backend.movie.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
@Tag(name = "Review", description = "Эндпоинты для создания, редактирования и удаления отзывов к фильмам.")
public class ReviewController {
    @Operation(
            summary = "Получить отзывы к фильму",
            description = "В ответе возвращается список отзывов"
    )
    @GetMapping("/{movieId}")
    public ResponseEntity<?> getReviews(@PathVariable UUID movieId) {
        return ResponseEntity.ok(null);
    }
    @Operation(
            summary = "Написать отзыв к фильму",
            description = "В ответе возвращается новый отзыв пользователя."
    )
    @PostMapping("/{movieId}")
    public ResponseEntity<?> createReview(@PathVariable UUID movieId) {
        return ResponseEntity.ok(null);
    }
    @Operation(
            summary = "Изменить написанный отзыв",
            description = "В ответе возвращается измененный отзыв."
    )
    @PutMapping("/{movieId}")
    public ResponseEntity<?> editReview(@PathVariable UUID movieId) {
        return ResponseEntity.ok(null);
    }
    @Operation(
            summary = "Удалить отзыв",
            description = "В ответе возвращается статус с кодом операции."
    )
    @DeleteMapping("/{movieId}")
    public ResponseEntity<?> deleteReview(@PathVariable UUID movieId) {
        return ResponseEntity.ok(null);
    }
}
