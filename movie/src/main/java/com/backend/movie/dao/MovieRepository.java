package com.backend.movie.dao;

import com.backend.movie.domain.entities.MovieEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, UUID> {
    List<MovieEntity> findAll(Specification<MovieEntity> movieEntitySpecification, Pageable pageable);
}
