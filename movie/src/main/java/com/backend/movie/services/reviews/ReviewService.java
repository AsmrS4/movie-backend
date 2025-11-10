package com.backend.movie.services.reviews;

import com.backend.movie.domain.models.Review;
import com.backend.movie.domain.requests.EditReviewRequest;
import com.backend.movie.domain.requests.ReviewRequest;

import java.util.List;
import java.util.UUID;

public interface ReviewService {
    List<Review> getReviews(UUID movieId);
    Review createReview(UUID movieId, ReviewRequest request);
    Review editReview(EditReviewRequest request);
    boolean deleteReview(UUID reviewId);

}
