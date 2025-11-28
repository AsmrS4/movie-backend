package com.backend.movie.dao;

import com.backend.movie.domain.entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, UUID> {
    @Query("SELECT g FROM MovieEntity m JOIN m.genres g WHERE m.movieId=:movieId")
    List<GenreEntity> findAllByMovie(@Param("movieId") UUID movieId);

}
