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
        
        ScoreSet scoreSet = scoreSetRepository.findByCafeId(cafeId).orElse(null);
        cafe.setScoreSet(scoreSet);

        return cafe;
    }

}
