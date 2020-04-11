package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.Review;
import com.cagong.caferanking.domain.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;

    public List<Review> getReviewsByCafeId(Long cafeId) {
        return reviewRepository.findAllByCafeId(cafeId);
    }
}
