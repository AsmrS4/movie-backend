package com.backend.movie.services.reviews;

import com.backend.movie.domain.models.Review;
import com.backend.movie.domain.requests.EditReviewRequest;
import com.backend.movie.domain.requests.ReviewRequest;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.UUID;

public interface ReviewService {
    List<Review> getReviews(UUID movieId);
    Review createReview(UUID movieId, ReviewRequest request) throws BadRequestException;
    Review editReview(EditReviewRequest request);
    boolean deleteReview(UUID reviewId);

}
