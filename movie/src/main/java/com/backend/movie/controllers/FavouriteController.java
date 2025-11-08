package com.backend.movie.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/favourites")
@Tag(name = "Favourites", description = "Эндпоинты для добавления фильмов в список избранных и получения списка добавленных фильмов в избранное.")
public class FavouriteController {
    @Operation(
            summary = "Получить список избранных фильмов",
            description = "В ответе возвращается список фильмов, добавленных в избранное."
    )
    @GetMapping
    public ResponseEntity<?> getFavourites() {
        return ResponseEntity.ok(null);
    }

    @Operation(
            summary = "Добавить фильм в избранное",
            description = "В ответе возвращается фильм, добавленный в избранное."
    )
    @PostMapping("/{movieId}")
    public ResponseEntity<?> addToFavourites(@PathVariable UUID movieId) {
        return ResponseEntity.ok(null);
    }

    @Operation(
            summary = "Удалить фильм из избранного",
            description = "В ответе возвращается статус с кодом операции."
    )
    @DeleteMapping("/{movieId}")
    public ResponseEntity<?> deleteFromFavourites(@PathVariable UUID movieId) {
        return ResponseEntity.ok(null);
    }

}
