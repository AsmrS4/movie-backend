package com.backend.movie.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @PostMapping("/{movieId}")
    public ResponseEntity<?> createReview(@PathVariable UUID movieId) {
        return ResponseEntity.ok(null);
    }
    @PutMapping("/{movieId}")
    public ResponseEntity<?> editReview(@PathVariable UUID movieId) {
        return ResponseEntity.ok(null);
    }
    @DeleteMapping("/{movieId}")
    public ResponseEntity<?> deleteReview(@PathVariable UUID movieId) {
        return ResponseEntity.ok(null);
    }
}
