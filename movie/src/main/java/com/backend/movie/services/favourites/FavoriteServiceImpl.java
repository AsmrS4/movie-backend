package com.backend.movie.services.favourites;

import com.backend.movie.dao.FavouriteRepository;
import com.backend.movie.dao.MovieRepository;
import com.backend.movie.dao.UserRepository;
import com.backend.movie.domain.entities.Favourites;
import com.backend.movie.domain.entities.MovieEntity;
import com.backend.movie.domain.entities.UserEntity;
import com.backend.movie.domain.models.Movie;
import com.backend.movie.exceptions.UnauthorizedException;
import com.backend.movie.mappers.MovieMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoriteServiceImpl implements FavouriteService{
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final FavouriteRepository favouriteRepository;
    private final MovieMapper movieMapper;
    @Override
    public List<Movie> getFavourites() {
        UserEntity user = getUserFromContext();
        List<MovieEntity> favouriteMovies = favouriteRepository.findFavouriteMovies(user.getId());
        return favouriteMovies.stream().map(movieMapper::toMovie).collect(Collectors.toList());
    }

    @Override
    public Movie addToFavourite(UUID movieId) throws BadRequestException {
        MovieEntity movie = movieRepository.findById(movieId)
                .orElseThrow(
                        () -> new NotFoundException("Фильм не найден")
                );
        UserEntity user = getUserFromContext();

        save(movie, user);

        return movieMapper.toMovie(movie);
    }

    @Override
    @Transactional
    public boolean deleteFromFavorite(UUID movieId) {
        MovieEntity movie = movieRepository.findById(movieId)
                .orElseThrow(
                        () -> new NotFoundException("Фильм не найден")
                );
        UserEntity user = getUserFromContext();
        favouriteRepository.deleteByUserAndMovie(user, movie);
        return true;
    }

    private UserEntity getUserFromContext() {
        try {
            UUID userId = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
            return userRepository.getReferenceById(userId);
        } catch (Exception ex) {
            log.error("RECEIVED EXCEPTION: " + ex);
            throw new UnauthorizedException("Вы не авторизованы");
        }
    }
    private void save(MovieEntity movie, UserEntity user) throws BadRequestException {
        Favourites favourites = Favourites.builder()
                .user(user)
                .movie(movie)
                .build();
        try {
            favouriteRepository.save(favourites);
        } catch (Exception ex) {
            throw new BadRequestException("Этот фильм уже добавлен в список избранных");
        }
    }
}
