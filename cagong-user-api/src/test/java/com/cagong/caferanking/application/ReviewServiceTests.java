package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.entity.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

class ReviewServiceTests {

    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
//        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    public void addReview() {
        reviewService.addReview(1L, 2L, 4.5, 3.5, 2.5, 1.5,
                "Good place");

        verify(reviewRepository).save(any());
    }

}