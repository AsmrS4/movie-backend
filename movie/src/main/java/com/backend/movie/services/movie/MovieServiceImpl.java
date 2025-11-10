package com.backend.movie.services.movie;

import com.backend.movie.dao.GenreRepository;
import com.backend.movie.dao.MovieRepository;
import com.backend.movie.dao.ReviewRepository;
import com.backend.movie.domain.entities.GenreEntity;
import com.backend.movie.domain.entities.MovieEntity;
import com.backend.movie.domain.filter.CatalogueFilter;
import com.backend.movie.domain.models.Genre;
import com.backend.movie.domain.models.Movie;
import com.backend.movie.helpers.MovieSpecification;
import com.backend.movie.mappers.MovieMapper;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService{
    private final MovieRepository repository;
    private final ReviewRepository reviewRepository;
    private final GenreRepository genreRepository;
    private final MovieMapper movieMapper;
    public MovieServiceImpl(
            @Autowired MovieRepository movieRepository,
            @Autowired GenreRepository genreRepository,
            @Autowired ReviewRepository reviewRepository,
            @Autowired MovieMapper movieMapper
            ) {
        this.repository = movieRepository;
        this.genreRepository = genreRepository;
        this.reviewRepository = reviewRepository;
        this.movieMapper = movieMapper;
    }
    @Override
    public Movie getMovieDetails(UUID movieId) {
        MovieEntity movieEntity = repository.findById(movieId).orElseThrow(
                () -> new NotFoundException("Фильм не найден")
        );
        List<GenreEntity> genres = genreRepository.findAllByMovie(movieId);
        movieEntity.setGenres(genres);
        Movie movie = movieMapper.toMovie(movieEntity);
        movie.setRating(reviewRepository.calculateAverageRatingForMovie(movieEntity));
        return movie;
    }

    @Override
    public List<Movie> getCatalogue(CatalogueFilter filter, Pageable pageable) throws BadRequestException {
        if(filter.getMinYear()!=null && filter.getMaxYear()!=null) {
            if(filter.getMinYear() > filter.getMaxYear()) {
                throw new BadRequestException("Минимальное значение года выпуска не должно быть выше максимального");
            }
        }
        if(filter.getMinAgeLimit()!=null && filter.getMaxAgeLimit()!=null) {
            if(filter.getMinAgeLimit() > filter.getMaxAgeLimit()) {
                throw new BadRequestException("Минимальное значение возраста не должно быть выше максимального");
            }
        }
        List<MovieEntity> movieEntities = repository.findAll(MovieSpecification.withFilter(filter), pageable);
        //TODO: добавить фильтрацию по жанрам
        return movieEntities.stream().map(
                movieEntity -> {
                    Movie movie = movieMapper.toMovie(movieEntity);
                    movie.setRating(reviewRepository.calculateAverageRatingForMovie(movieEntity));
                    return movie;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public List<Genre> getAvailableMovieGenres() {
        List<GenreEntity> genres = genreRepository.findAll();
        return genres.stream().map(movieMapper::toGenre).collect(Collectors.toList());
    }

}
