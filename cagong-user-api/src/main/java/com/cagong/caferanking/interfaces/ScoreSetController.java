package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.ScoreSetService;
import com.cagong.caferanking.domain.entity.ScoreSet;
import com.cagong.caferanking.domain.network.response.ScoreSetApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cafes")
public class ScoreSetController {

    private ScoreSetService scoresService;

    // TODO: scoreset List 전부에 대한 GET도 해야하는지?
    // main에서 category별 scoreset 상위 5개를 select해서 보내야 한다.
    // parameter로 지정해주자
    // 필요한 데이터: 각 점수들, cafe name, imgUrl
    @GetMapping("/scoresets")
    public List<ScoreSetApiResponse> getScoreSets(@RequestParam("index") String category) {
        return scoresService.getScoreSets(category);
    }

    @GetMapping("/{cafeId}/scoreset")
    public ScoreSet getScoreSet(@PathVariable("cafeId") Long cafeId) {
        return scoresService.getScoreSetByCafeId(cafeId);
    }
}
