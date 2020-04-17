package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.Review;
import com.cagong.caferanking.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;

    public Review addReview(Long cafeId,
                            String userName,
                            double mood, double light, double price, double taste,
                            String comment) {

        Review review = Review.builder()
                .cafeId(cafeId)
                .userName(userName)
                .mood(mood).light(light).price(price).taste(taste)
                .comment(comment)
                .build();

        // TODO: 여기서 ScoreSet에 변경된 총 평점이 들어가도록 작업 필요
        return reviewRepository.save(review);
    }
}
