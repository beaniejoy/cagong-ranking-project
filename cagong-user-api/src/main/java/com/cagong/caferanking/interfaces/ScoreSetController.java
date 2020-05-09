package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.ScoreSetService;
import com.cagong.caferanking.domain.entity.ScoreSet;
import com.cagong.caferanking.domain.network.response.ScoreSetApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScoreSetController {

    @Autowired
    private ScoreSetService scoresService;

    @GetMapping("/scoresets")
    public List<ScoreSetApiResponse> getScoreSets(@RequestParam("index") String category) {
        return scoresService.getScoreSets(category);
    }
}
