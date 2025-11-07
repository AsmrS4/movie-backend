package com.backend.movie.mappers;

import com.backend.movie.domain.entities.GenreEntity;
import com.backend.movie.domain.entities.MovieEntity;
import com.backend.movie.domain.models.Genre;
import com.backend.movie.domain.models.Movie;
import com.backend.movie.services.movie.MovieService;
import com.backend.movie.services.users.UserService;
import org.mapstruct.Mapper;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, componentModel = "spring", uses = MovieService.class)
public interface MovieMapper {
    Movie toMovie(MovieEntity movieEntity);
    MovieEntity toMovieEntity(Movie movie);
    Genre toGenre(GenreEntity genreEntity);
    GenreEntity toGenreEntity(Genre genre);
}
