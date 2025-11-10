package com.backend.movie.mappers;

import com.backend.movie.domain.entities.ReviewEntity;
import com.backend.movie.domain.models.Review;
import com.backend.movie.domain.requests.EditReviewRequest;
import com.backend.movie.domain.requests.ReviewRequest;
import com.backend.movie.services.users.UserService;
import org.mapstruct.Mapper;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, componentModel = "spring")
public interface ReviewMapper {
    Review toReview(ReviewEntity review);
    ReviewEntity toReviewEntity(ReviewRequest request);
}
