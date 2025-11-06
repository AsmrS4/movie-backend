package com.backend.movie.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/favourites")
public class FavouriteController {
    @GetMapping
    public ResponseEntity<?> getFavourites() {
        return ResponseEntity.ok(null);
    }

    @PostMapping("/{movieId}")
    public ResponseEntity<?> addToFavourites(@PathVariable UUID movieId) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<?> deleteFromFavourites(@PathVariable UUID movieId) {
        return ResponseEntity.ok(null);
    }

}
