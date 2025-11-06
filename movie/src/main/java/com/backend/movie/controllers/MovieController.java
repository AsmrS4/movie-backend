package com.backend.movie.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/movie")
@Tag(name = "Movie catalogue", description = "Эндпоинты для получения информации о доступных фильмах в каталоге")
public class MovieController {

    @Operation(
            summary = "Получить каталог фильмов",
            description = "В ответе возвращается список фильмов с пагинацией."
    )
    @GetMapping("/catalogue")
    public ResponseEntity<?> getCatalogue() {
        return ResponseEntity.ok(null);
    }

    @Operation(
            summary = "Получить детальную информацию о фильме",
            description = "В ответе возвращается детальная информация о фильме."
    )
    @GetMapping("/{movieId}")
    public ResponseEntity<?> getMovieDetails(@PathVariable UUID movieId) {
        return ResponseEntity.ok(null);
    }

}
