package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.entity.Review;
import com.cagong.caferanking.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class ReviewServiceTests {

    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    public void getReviewByCafeId() {
        List<Review> mockReviews = new ArrayList<>();
        mockReviews.add(Review.builder()
//                .cafeId(1L)
                .mood(3.5)
                .light(4.5)
                .price(3.5)
                .taste(1.5)
                .comment("It's so Good!")
                .build());

        given(reviewRepository.findAllByCafeId(1L)).willReturn(mockReviews);
        List<Review> reviews = reviewService.getReviewsByCafeId(1L);

        verify(reviewRepository).findAllByCafeId(eq(1L));

        Review review = reviews.get(0);

        assertEquals(review.getComment(), "It's so Good!");
        assertEquals(review.getLight(), 4.5);

    }
}