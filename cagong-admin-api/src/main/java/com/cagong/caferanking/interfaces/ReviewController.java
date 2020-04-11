package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.ReviewService;
import com.cagong.caferanking.domain.Review;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ReviewController {

    private ReviewService reviewService;

    @GetMapping("/cafes/{cafeId}/reviews")
    public List<Review> list(@PathVariable("cafeId") Long cafeId) {
        return reviewService.getReviewsByCafeId(cafeId);
    }
}
