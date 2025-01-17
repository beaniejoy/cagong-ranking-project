package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.entity.ScoreSet;
import com.cagong.caferanking.error.ScoreSetNotFoundException;
import com.cagong.caferanking.domain.entity.ScoreSetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ScoreSetService {

    private ScoreSetRepository scoreSetRepository;

    public ScoreSet getScoreSetByCafeId(Long cafeId) {
        return scoreSetRepository.findByCafeId(cafeId).
                orElseThrow(() -> new ScoreSetNotFoundException(cafeId));
    }
}
