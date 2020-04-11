package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.ScoreSet;
import com.cagong.caferanking.domain.ScoreSetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

class ScoresServiceTests {

    private ScoreSetService scoresService;

    @Mock
    private ScoreSetRepository scoresRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        scoresService = new ScoreSetService(scoresRepository);
    }

    @Test
    public void getScoresByCafeIdWithExisted() {
        ScoreSet mockScores = ScoreSet.builder()
                .cafeId(1L)
                .light(4.5)
                .mood(3.5)
                .price(2.5)
                .taste(1.5)
                .build();

        given(scoresRepository.findByCafeId(1L)).willReturn(Optional.ofNullable(mockScores));

        ScoreSet scores = scoresService.getScoreSetByCafeId(1L);

        assertEquals(scores.getCafeId(), 1);
        assertEquals(scores.getLight(), 4.5);
    }

    @Test
    public void getScoresByCafeIdWithNotExisted() {
        assertThrows(ScoreSetNotFoundException.class,
                () -> scoresService.getScoreSetByCafeId(404L));
    }
}