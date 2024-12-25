package org.example.service;

import org.example.entity.Car;
import org.example.entity.Client;
import org.example.entity.Review;
import org.example.exception.NotFoundReviewException;
import org.example.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Service for Review Entity
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review addReview(Client client, Car car, String text, int rating) {
        if (client == null || car == null) {
            throw new IllegalArgumentException("Client and Car must not be null");
        }
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Review text must not be empty");
        }
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        Review review = new Review();
        review.setClient(client);
        review.setCar(car);
        review.setText(text);
        review.setRating(rating);

        return reviewRepository.save(review);
    } //Add more exception info

    public List<Review> searchReviews(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException("Keyword must not be empty");
        }
        return reviewRepository.findByTextContainingIgnoreCase(keyword);
    }

    public List<Review> searchReviewByKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException("Keyword must not be empty");
        }
        return reviewRepository.searchReviewsByKeyword(keyword);
    }

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, Review updatedReview) {
        Optional<Review> existingReview = reviewRepository.findById(id);
        if (existingReview.isPresent()) {
            Review review = existingReview.get();
            review.setText(updatedReview.getText());
            review.setRating(updatedReview.getRating());
            review.setClient(updatedReview.getClient());
            review.setCar(updatedReview.getCar());
            return reviewRepository.save(review);
        } else {
            throw new NotFoundReviewException(id);
        }
    }

    public void deleteReview(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
        } else {
            throw new NotFoundReviewException(id);
        }
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }
}
