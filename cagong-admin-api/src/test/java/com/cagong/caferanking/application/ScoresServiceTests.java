package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.entity.ScoreSet;
import com.cagong.caferanking.error.ScoreSetNotFoundException;
import com.cagong.caferanking.repository.ScoreSetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

class ScoresServiceTests {

    private ScoreSetService scoreSetService;

    @Mock
    private ScoreSetRepository scoresRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        scoreSetService = new ScoreSetService(scoresRepository);
    }

    @Test
    public void getScoresByCafeIdWithExisted() {
        ScoreSet mockScoreSet = ScoreSet.builder()
//                .cafeId(1L)
                .light(4.5)
                .mood(3.5)
                .price(2.5)
                .taste(1.5)
                .build();

        given(scoresRepository.findByCafeId(1L)).willReturn(Optional.ofNullable(mockScoreSet));

        ScoreSet scoreSet = scoreSetService.getScoreSetByCafeId(1L);

        assertEquals(scoreSet.getLight(), 4.5);
    }

    @Test
    public void getScoresByCafeIdWithNotExisted() {
        assertThrows(ScoreSetNotFoundException.class,
                () -> scoreSetService.getScoreSetByCafeId(404L));
    }
}