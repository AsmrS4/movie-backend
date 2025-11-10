package com.backend.movie.services.reviews;

import com.backend.movie.dao.MovieRepository;
import com.backend.movie.dao.ReviewRepository;
import com.backend.movie.dao.UserRepository;
import com.backend.movie.domain.entities.MovieEntity;
import com.backend.movie.domain.entities.UserEntity;
import com.backend.movie.domain.models.Review;
import com.backend.movie.domain.requests.EditReviewRequest;
import com.backend.movie.domain.requests.ReviewRequest;
import com.backend.movie.helpers.UserContextManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    @Override
    public List<Review> getReviews(UUID movieId) {
        MovieEntity movieEntity = movieRepository.findById(movieId)
                .orElseThrow(
                        () -> new NotFoundException("Фильм не найден")
                );
        return null;
    }

    @Override
    public Review createReview(UUID movieId, ReviewRequest request) {
        UserEntity currentUser = UserContextManager.getUserFromContext(userRepository);
        MovieEntity movieEntity = movieRepository.findById(movieId)
                .orElseThrow(() -> new NotFoundException("Фильм не найден"));
        return null;
    }

    @Override
    public Review editReview(EditReviewRequest request) {
        UserEntity currentUser = UserContextManager.getUserFromContext(userRepository);
        return null;
    }

    @Override
    public boolean deleteReview(UUID reviewId) {
        UserEntity currentUser = UserContextManager.getUserFromContext(userRepository);
        return false;
    }
}
