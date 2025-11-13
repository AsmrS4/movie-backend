package com.backend.movie.services.reviews;

import com.backend.movie.dao.MovieRepository;
import com.backend.movie.dao.ReviewRepository;
import com.backend.movie.dao.UserRepository;
import com.backend.movie.domain.entities.MovieEntity;
import com.backend.movie.domain.entities.ReviewEntity;
import com.backend.movie.domain.entities.UserEntity;
import com.backend.movie.domain.models.Review;
import com.backend.movie.domain.requests.EditReviewRequest;
import com.backend.movie.domain.requests.ReviewRequest;
import com.backend.movie.helpers.UserContextManager;
import com.backend.movie.mappers.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService{
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper mapper;
    @Override
    public List<Review> getReviews(UUID movieId) {
        MovieEntity movie = movieRepository.findById(movieId)
                .orElseThrow(
                        () -> new NotFoundException("Фильм не найден")
                );
        List<ReviewEntity> movieReviewEntities = reviewRepository.findAllByMovie(movie);
        return movieReviewEntities.stream().map(
                mapper::toReview
        ).collect(Collectors.toList());
    }

    @Override
    public Review createReview(UUID movieId, ReviewRequest request) throws BadRequestException {
        UserEntity currentUser = UserContextManager.getUserFromContext(userRepository);
        MovieEntity movieEntity = movieRepository.findById(movieId)
                .orElseThrow(() -> new NotFoundException("Фильм не найден"));
        ReviewEntity reviewEntity = mapper.toReviewEntity(request);
        reviewEntity.setAuthor(currentUser);
        reviewEntity.setMovie(movieEntity);
        try {
            reviewRepository.save(reviewEntity);
        } catch (Exception ex) {
            throw new BadRequestException("Вы можете написать не более 1 отзыва к фильму");
        }
        return mapper.toReview(reviewEntity);
    }

    @Override
    public Review editReview(EditReviewRequest request) {
        ReviewEntity reviewEntity= reviewRepository.findById(request.getId()).orElseThrow(
                () -> new NotFoundException("Отзыв не найден")
        );
        if(!isAuthorOfReview(reviewEntity)) {
            throw new AccessDeniedException("Вы не можете редактировать чужой отзыв");
        }

        reviewEntity.setComment(request.getComment());
        reviewEntity.setRating(request.getRating());
        reviewEntity.setAnonymous(request.isAnonymous());
        reviewRepository.save(reviewEntity);

        return mapper.toReview(reviewEntity);
    }

    @Override
    public boolean deleteReview(UUID reviewId) {
        ReviewEntity reviewEntity = reviewRepository.findById(reviewId).orElseThrow(
                () -> new NotFoundException("Отзыв не найден")
        );
        if(!isAuthorOfReview(reviewEntity)) {
            throw new AccessDeniedException("Вы не можете удалить чужой отзыв");
        }
        reviewRepository.delete(reviewEntity);
        return true;
    }

    private boolean isAuthorOfReview(ReviewEntity review) {
        UserEntity currentUser = UserContextManager.getUserFromContext(userRepository);
        return review.getAuthor().equals(currentUser);
    }
}
