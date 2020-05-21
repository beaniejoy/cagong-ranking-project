package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.entity.Cafe;
import com.cagong.caferanking.domain.entity.ScoreSet;
import com.cagong.caferanking.domain.network.response.ScoreSetApiResponse;
import com.cagong.caferanking.error.CafeNotFoundException;
import com.cagong.caferanking.error.ScoreSetNotFoundException;
import com.cagong.caferanking.domain.entity.CafeRepository;
import com.cagong.caferanking.domain.entity.ScoreSetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ScoreSetService {

    private ScoreSetRepository scoreSetRepository;

    private CafeRepository cafeRepository;

    public List<ScoreSetApiResponse> getScoreSets(String category) {
        List<ScoreSet> scoreSetList = new ArrayList<>();

        switch (category) {
            case "mood": scoreSetList = scoreSetRepository.findTop5ByOrderByMoodDesc(); break;
            case "light": scoreSetList = scoreSetRepository.findTop5ByOrderByLightDesc(); break;
            case "price": scoreSetList = scoreSetRepository.findTop5ByOrderByPriceDesc(); break;
            case "taste": scoreSetList = scoreSetRepository.findTop5ByOrderByTasteDesc(); break;
            default: break;
        }

        return scoreSetList.stream()
                .map(this::response)
                .map(scoreSetApiResponse -> {
                    Cafe cafe = cafeRepository.findById(scoreSetApiResponse.getCafeId())
                            .orElseThrow(() -> new CafeNotFoundException(scoreSetApiResponse.getCafeId()));

                    scoreSetApiResponse.setCafeName(cafe.getName());
                    scoreSetApiResponse.setCafeImgUrl(cafe.getImgUrl());

                    return scoreSetApiResponse;
                })
                .collect(Collectors.toList());
    }

    // TODO: Optional 어떻게 처리할 것인지 알아보자
    public ScoreSet getScoreSetByCafeId(Long cafeId) {
        return scoreSetRepository.findByCafeId(cafeId).
                orElseThrow(() -> new ScoreSetNotFoundException(cafeId));
    }

    public ScoreSetApiResponse response(ScoreSet scoreSet) {

        return ScoreSetApiResponse.builder()
                .id(scoreSet.getId())
                .mood(scoreSet.getMood())
                .light(scoreSet.getLight())
                .price(scoreSet.getPrice())
                .taste(scoreSet.getTaste())
                .cafeId(scoreSet.getCafe().getId())
                .build();
    }


}
