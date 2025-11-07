package com.backend.movie.services.movie;

import com.backend.movie.dao.GenreRepository;
import com.backend.movie.dao.MovieRepository;
import com.backend.movie.domain.entities.GenreEntity;
import com.backend.movie.domain.filter.CatalogueFilter;
import com.backend.movie.domain.models.Genre;
import com.backend.movie.domain.models.Movie;
import com.backend.movie.mappers.MovieMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService{
    private final MovieRepository repository;
    private final GenreRepository genreRepository;
    private final MovieMapper movieMapper;
    public MovieServiceImpl(
            @Autowired MovieRepository movieRepository,
            @Autowired GenreRepository genreRepository,
            @Autowired MovieMapper movieMapper
            ) {
        this.repository = movieRepository;
        this.genreRepository = genreRepository;
        this.movieMapper = movieMapper;
    }
    @Override
    public Movie getMovieDetails(UUID movieId) {
        return null;
    }

    @Override
    public List<Movie> getCatalogue(CatalogueFilter filter, Pageable pageable) {
        return null;
    }

    @Override
    public List<String> getMovieDirectors() {
        return null;
    }

    @Override
    public List<String> getMovieCountries() {
        return null;
    }

    @Override
    public Set<Genre> getAvailableMovieGenres() {
        Set<GenreEntity> genres = (Set<GenreEntity>) genreRepository.findAll();
        return genres.stream().map(movieMapper::toGenre).collect(Collectors.toSet());
    }
}
