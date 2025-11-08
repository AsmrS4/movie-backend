package com.backend.movie.services.favourites;

import com.backend.movie.domain.models.Movie;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.UUID;

public interface FavouriteService {
    List<Movie> getFavourites();
    Movie addToFavourite(UUID movieId) throws BadRequestException;
    boolean deleteFromFavorite(UUID movieId);
}
