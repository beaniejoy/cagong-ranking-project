package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CafeService {

    private CafeRepository cafeRepositoy;
    private CafeMenuRepository cafeMenuRepository;
    private ReviewRepository reviewRepository;
    private ScoreSetRepository scoreSetRepository;

    public List<Cafe> getCafes() {
        return cafeRepositoy.findAll();
    }

    public Cafe getCafe(Long cafeId) {
        Cafe cafe = cafeRepositoy.findById(cafeId)
                .orElseThrow(() -> new CafeNotFoundException(cafeId));

        List<CafeMenu> cafeMenus = cafeMenuRepository.findAllByCafeId(cafeId);
        cafe.setCafeMenus(cafeMenus);

        List<Review> reviews = reviewRepository.findAllByCafeId(cafeId);
        cafe.setReviews(reviews);

        // user-api에서는 review의 score평가가 아무것도 없을 때 그냥 null을 반환하도록
        ScoreSet scoreSet = scoreSetRepository.findByCafeId(cafeId).orElse(null);
        cafe.setScoreSet(scoreSet);

        return cafe;
    }

}
