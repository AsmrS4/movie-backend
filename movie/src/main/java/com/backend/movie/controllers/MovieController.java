package com.backend.movie.controllers;

import com.backend.movie.domain.enums.SortType;
import com.backend.movie.domain.filter.CatalogueFilter;
import com.backend.movie.services.movie.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/movie")
@Tag(name = "Movie catalogue", description = "Эндпоинты для получения информации о доступных фильмах в каталоге")
public class MovieController {
    private final MovieService movieService;
    public MovieController(@Autowired MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(
            summary = "Получить каталог фильмов",
            description = "В ответе возвращается список фильмов с пагинацией."
    )
    @GetMapping("/catalogue")
    public ResponseEntity<?> getCatalogue(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) @Validated @Min(value = 1 , message = "Минимальное значение 1") Integer minYear,
            @RequestParam(required = false) @Validated @Min(value = 1 , message = "Минимальное значение 1") Integer maxYear,
            @RequestParam(required = false) @Validated @Min(value = 0 , message = "Значение должно быть положительным") Integer minAgeLimit,
            @RequestParam(required = false) @Validated @Min(value = 0 , message = "Значение должно быть положительным") Integer maxAgeLimit,
            @RequestParam(required = false) SortType sortBy,
            @RequestParam(required = false, defaultValue = "1") @Validated @Min(value = 1 , message = "Минимальное значение 1") int page,
            @RequestParam(required = false, defaultValue = "10") @Validated @Min(value = 1 , message = "Минимальное значение 1") int size
            ) throws BadRequestException {
        CatalogueFilter filter = new CatalogueFilter(search, minYear, maxYear, minAgeLimit, maxAgeLimit, sortBy);
        Pageable pageable = PageRequest.of(--page, size);
        return ResponseEntity.ok(movieService.getCatalogue(filter, pageable));
    }

    @Operation(
            summary = "Получить детальную информацию о фильме",
            description = "В ответе возвращается детальная информация о фильме."
    )
    @GetMapping("/{movieId}")
    public ResponseEntity<?> getMovieDetails(@PathVariable UUID movieId) {
        return ResponseEntity.ok(movieService.getMovieDetails(movieId));
    }
    @GetMapping("/catalogue/genres")
    public ResponseEntity<?> getGenres() {
        return ResponseEntity.ok(movieService.getAvailableMovieGenres());
    }
}
