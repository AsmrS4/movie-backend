package com.backend.movie.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    @GetMapping("/catalogue")
    public ResponseEntity<?> getCatalogue() {
        return ResponseEntity.ok(null);
    }
    @GetMapping("/{movieId}")
    public ResponseEntity<?> getMovieDetails(@PathVariable UUID movieId) {
        return ResponseEntity.ok(null);
    }

}
