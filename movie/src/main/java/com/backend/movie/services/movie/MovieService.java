package com.backend.movie.services.movie;

import com.backend.movie.domain.entities.GenreEntity;
import com.backend.movie.domain.filter.CatalogueFilter;
import com.backend.movie.domain.models.Genre;
import com.backend.movie.domain.models.Movie;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface MovieService {
    Movie getMovieDetails(UUID movieId);
    List<Movie> getCatalogue(CatalogueFilter filter,  Pageable pageable) throws BadRequestException;
    List<Genre> getAvailableMovieGenres();
}
