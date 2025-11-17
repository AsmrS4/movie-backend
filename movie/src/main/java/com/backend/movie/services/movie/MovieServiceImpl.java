package com.backend.movie.services.movie;

import com.backend.movie.dao.GenreRepository;
import com.backend.movie.dao.MovieRepository;
import com.backend.movie.dao.PageableMovieRepository;
import com.backend.movie.dao.ReviewRepository;
import com.backend.movie.domain.entities.GenreEntity;
import com.backend.movie.domain.entities.MovieEntity;
import com.backend.movie.domain.filter.CatalogueFilter;
import com.backend.movie.domain.models.Genre;
import com.backend.movie.domain.models.Movie;
import com.backend.movie.domain.response.MoviePaginatedRequest;
import com.backend.movie.domain.response.Pagination;
import com.backend.movie.helpers.MovieSpecification;
import com.backend.movie.mappers.MovieMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{
    private final MovieRepository repository;
    private final PageableMovieRepository pageableMovieRepository;
    private final ReviewRepository reviewRepository;
    private final GenreRepository genreRepository;
    private final MovieMapper movieMapper;
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
    public MoviePaginatedRequest getCatalogue(CatalogueFilter filter, Pageable pageable) throws BadRequestException {
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
        if(filter.getGenres()!=null && !filter.getGenres().isEmpty()) {
            filter.getGenres().stream().map(genre -> {
                if(!genreRepository.existsById(genre)) {
                    throw new EntityNotFoundException("Указанный жанр не найден");
                }
                return null;
            }).close();
        }
        Page<MovieEntity> pageableMovieEntities = pageableMovieRepository.findAll(MovieSpecification.withFilter(filter), pageable);
        List<Movie> mappedMovies = toMovies(pageableMovieEntities);
        Pagination pagination = toPagination(pageableMovieEntities);

        return MoviePaginatedRequest.builder()
                .movies(mappedMovies)
                .pagination(pagination)
                .build();

    }

    @Override
    public List<Genre> getAvailableMovieGenres() {
        List<GenreEntity> genres = genreRepository.findAll();
        return genres.stream().map(movieMapper::toGenre).collect(Collectors.toList());
    }

    private Pagination toPagination(Page<?> pageableResponse) {
        return Pagination.builder()
                .current(pageableResponse.getNumber() + 1)
                .count(pageableResponse.getTotalPages())
                .size(pageableResponse.getSize())
                .build();
    }
    private List<Movie> toMovies(Page<MovieEntity> content) {
        return content.getContent().stream().map(
                movieEntity -> {
                    Movie movie = movieMapper.toMovie(movieEntity);
                    movie.setRating(reviewRepository.calculateAverageRatingForMovie(movieEntity));
                    return movie;
                }
        ).toList();
    }
}
