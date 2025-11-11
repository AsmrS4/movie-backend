package com.backend.movie.dao;

import com.backend.movie.domain.entities.Favourites;
import com.backend.movie.domain.entities.MovieEntity;
import com.backend.movie.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourites, Long> {
    @Modifying
    @Query("DELETE FROM Favourites f WHERE f.user=:user AND f.movie=:movie")
    void deleteByUserAndMovie(@Param("user") UserEntity user, @Param("movie") MovieEntity movie);
    @Query("SELECT m FROM MovieEntity m RIGHT JOIN Favourites f ON m.movieId=f.movie.movieId WHERE f.user.id =:userId")
    List<MovieEntity> findFavouriteMovies(UUID userId);
}
