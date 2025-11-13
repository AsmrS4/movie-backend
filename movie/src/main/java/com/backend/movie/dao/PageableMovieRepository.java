package com.backend.movie.dao;

import com.backend.movie.domain.entities.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PageableMovieRepository extends PagingAndSortingRepository<MovieEntity, UUID> {
    Page<MovieEntity> findAll(Specification<MovieEntity> movieEntitySpecification, Pageable pageable);
}
