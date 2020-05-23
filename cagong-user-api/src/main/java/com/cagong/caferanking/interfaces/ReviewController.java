package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.ReviewService;
import com.cagong.caferanking.domain.network.request.ReviewApiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/cafes/{cafeId}/reviews")
    public Long create(
            @PathVariable("cafeId") Long cafeId,
            @RequestBody ReviewApiRequest resource) {

        // TODO: token을 이용한 userName 받아오기
        return reviewService.addReview(cafeId, resource).getId();
    }

    @PutMapping("/cafes/{cafeId}/reviews/{reviewId}")
    public Long update(
            @PathVariable Long reviewId,
            @PathVariable Long cafeId,
            @RequestBody ReviewApiRequest resource) {

        return reviewService.updateReview(reviewId, cafeId, resource).getId();
    }

}
