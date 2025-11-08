package com.backend.movie.dao;

import com.backend.movie.domain.entities.MovieEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, UUID> {
    @Query("SELECT DISTINCT country FROM MovieEntity me")
    Set<String> findAllCountries();
    @Query("SELECT DISTINCT director FROM MovieEntity me")
    Set<String> findAllDirectors();
    List<MovieEntity> findAll(Specification<MovieEntity> movieEntitySpecification, Pageable pageable);
}
