package com.backend.movie.dao;

import com.backend.movie.domain.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, UUID> {
}
