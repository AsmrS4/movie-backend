package com.backend.movie.dao;

import com.backend.movie.domain.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, UUID> {
    @Query("SELECT DISTINCT country FROM MovieEntity me")
    Set<String> findAllCountries();
    @Query("SELECT DISTINCT director FROM MovieEntity me")
    Set<String> findAllDirectors();
}
