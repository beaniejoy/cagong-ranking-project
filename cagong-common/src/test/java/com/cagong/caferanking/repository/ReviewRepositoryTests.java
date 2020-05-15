package com.cagong.caferanking.repository;

import com.cagong.caferanking.CaferankingCommonApplicationTests;
import com.cagong.caferanking.domain.entity.Review;
import com.cagong.caferanking.domain.network.response.ScoreSetApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class ReviewRepositoryTests extends CaferankingCommonApplicationTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CafeRepository cafeRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void average() {
        Object result = reviewRepository.findAverageByCafeId(1L);
        Object[] out = (Object[]) result;

        log.info(String.valueOf(out[0]));
        log.info(String.valueOf(out[1]));
        log.info(String.valueOf(out[2]));
        log.info(String.valueOf(out[3]));
        log.info(String.valueOf(out[4]));
    }

    @Test
    public void save() {
        Review review = reviewRepository.save(Review.builder()
                .cafe(cafeRepository.getOne(1L))
                .user(userRepository.getOne(6L))
                .mood(3.0)
                .light(4.5)
                .price(1.5)
                .taste(0.5)
                .comment("아주 마니 사랑해요~")
                .build());

        log.info(review.getCafe().getName());
    }

}