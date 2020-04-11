package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.ScoreSetService;
import com.cagong.caferanking.domain.ScoreSet;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ScoreSetController {

    private ScoreSetService scoresService;

    @GetMapping("/cafes/{cafeId}/scoreset")
    public ScoreSet getScoreSet(@PathVariable("cafeId") Long cafeId) {
        return scoresService.getScoreSetByCafeId(cafeId);
    }
}