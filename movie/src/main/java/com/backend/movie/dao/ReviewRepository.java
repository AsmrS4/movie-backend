package com.backend.movie.dao;

import com.backend.movie.domain.entities.MovieEntity;
import com.backend.movie.domain.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, UUID> {
    List<ReviewEntity> findAllByMovie(MovieEntity movie);
    @Query("SELECT COALESCE(AVG(re.rating), 0) FROM ReviewEntity re WHERE re.movie=:movie")
    double calculateAverageRatingForMovie(@Param("movie") MovieEntity movie);
}
