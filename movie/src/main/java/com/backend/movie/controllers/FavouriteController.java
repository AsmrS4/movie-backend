package com.backend.movie.controllers;

import com.backend.movie.services.favourites.FavouriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/favourites")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Favourites", description = "Эндпоинты для добавления фильмов в список избранных и получения списка добавленных фильмов в избранное.")
public class FavouriteController {
    @Autowired
    private FavouriteService favouriteService;
    @Operation(
            summary = "Получить список избранных фильмов",
            description = "В ответе возвращается список фильмов, добавленных в избранное."
    )
    @GetMapping
    public ResponseEntity<?> getFavourites() {
        return ResponseEntity.ok(favouriteService.getFavourites());
    }

    @Operation(
            summary = "Добавить фильм в избранное",
            description = "В ответе возвращается фильм, добавленный в избранное."
    )
    @PostMapping("/{movieId}")
    public ResponseEntity<?> addToFavourites(@PathVariable UUID movieId) throws BadRequestException {
        return ResponseEntity.ok(favouriteService.addToFavourite(movieId));
    }

    @Operation(
            summary = "Удалить фильм из избранного",
            description = "В ответе возвращается статус с кодом операции."
    )
    @DeleteMapping("/{movieId}")
    public ResponseEntity<?> deleteFromFavourites(@PathVariable UUID movieId) {
        return ResponseEntity.ok(favouriteService.deleteFromFavorite(movieId));
    }

}
