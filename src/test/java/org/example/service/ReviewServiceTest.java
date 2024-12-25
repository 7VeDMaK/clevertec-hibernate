package org.example.service;

import org.example.entity.Car;
import org.example.entity.Client;
import org.example.entity.Review;
import org.example.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    public ReviewServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddReview_Success() {
        // Given
        Client client = new Client();
        Car car = new Car();
        Review review = new Review(null, "Great car!", 5, client, car);

        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        // When
        Review result = reviewService.addReview(client, car, "Great car!", 5);

        // Then
        assertEquals("Great car!", result.getText());
        assertEquals(5, result.getRating());
        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void testSearchReviews_Success() {
        // Given
        Review review1 = new Review(1L, "Great experience!", 5, null, null);
        Review review2 = new Review(2L, "Not bad", 4, null, null);

        when(reviewRepository.findByTextContainingIgnoreCase("great")).thenReturn(List.of(review1));

        // When
        List<Review> results = reviewService.searchReviews("great");

        // Then
        assertEquals(1, results.size());
        assertEquals("Great experience!", results.get(0).getText());
        verify(reviewRepository, times(1)).findByTextContainingIgnoreCase("great");
    }
}
