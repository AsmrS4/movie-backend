package com.backend.movie.services.movie;

import com.backend.movie.dao.movie.MovieRepository;
import com.backend.movie.domain.filter.CatalogueFilter;
import com.backend.movie.domain.models.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieServiceImpl implements MovieService{
    private MovieRepository repository;
    public MovieServiceImpl(@Autowired MovieRepository movieRepository) {
        this.repository = movieRepository;
    }
    @Override
    public Movie getMovieDetails(UUID movieId) {
        return null;
    }

    @Override
    public List<Movie> getCatalogue(CatalogueFilter filter, Pageable pageable) {
        return null;
    }
}
