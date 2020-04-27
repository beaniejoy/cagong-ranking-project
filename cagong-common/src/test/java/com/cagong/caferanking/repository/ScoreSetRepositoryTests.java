package com.cagong.caferanking.repository;

import com.cagong.caferanking.CaferankingCommonApplicationTests;
import com.cagong.caferanking.domain.entity.ScoreSet;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class ScoreSetRepositoryTests extends CaferankingCommonApplicationTests {

    @Autowired
    private ScoreSetRepository scoreSetRepository;

    @Test
    public void read() {
        List<ScoreSet> scoreSetList = scoreSetRepository.findTop5ByOrderByMoodDesc();
        scoreSetList.stream()
                .map(scoreSet -> {
                    log.info(scoreSet.toString());
                    return scoreSet;
                });
    }

}