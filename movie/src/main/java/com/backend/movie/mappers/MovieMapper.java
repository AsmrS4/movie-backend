package com.backend.movie.mappers;

import com.backend.movie.domain.entities.GenreEntity;
import com.backend.movie.domain.entities.MovieEntity;
import com.backend.movie.domain.models.Genre;
import com.backend.movie.domain.models.Movie;
import com.backend.movie.services.movie.MovieService;
import com.backend.movie.services.users.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, componentModel = "spring")
public interface MovieMapper {
    @Mapping(source = "filmYear", target = "filmYear")
    @Mapping(source = "genres", target = "genres")
    Movie toMovie(MovieEntity movieEntity);
    Genre toGenre(GenreEntity genreEntity);
    GenreEntity toGenreEntity(Genre genre);
}
