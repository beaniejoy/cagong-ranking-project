package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.ReviewService;
import com.cagong.caferanking.domain.Review;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@AllArgsConstructor
public class ReviewController {

    private ReviewService reviewService;

    @PostMapping("/cafes/{cafeId}/reviews")
    public ResponseEntity<?> create(
            @PathVariable("cafeId") Long cafeId,
            @Valid @RequestBody Review resource)
            throws URISyntaxException {

        // TODO: token을 이용한 userName 받아오기

        Review review = reviewService.addReview(cafeId,
                "Joy",
                resource.getMood(), resource.getLight(), resource.getPrice(), resource.getTaste(),
                resource.getComment());

        String url = "/cafes/" + cafeId + "/reviews/" + review.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
