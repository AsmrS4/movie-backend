package com.backend.movie.services.movie;

import com.backend.movie.domain.filter.CatalogueFilter;
import com.backend.movie.domain.models.Genre;
import com.backend.movie.domain.models.Movie;
import com.backend.movie.domain.response.MoviePaginatedRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface MovieService {
    Movie getMovieDetails(UUID movieId);
    MoviePaginatedRequest getCatalogue(CatalogueFilter filter, Pageable pageable) throws BadRequestException;
    List<Genre> getAvailableMovieGenres();
}
