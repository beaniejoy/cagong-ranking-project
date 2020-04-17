package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.ScoreSet;
import com.cagong.caferanking.repository.ScoreSetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class ScoreSetServiceTests {

    private ScoreSetService scoreSetService;

    @Mock
    private ScoreSetRepository scoreSetRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        scoreSetService = new ScoreSetService(scoreSetRepository);
    }

    @Test
    public void getScoreSetByCafeIdWithExisted() {

        ScoreSet mockScoreSet = ScoreSet.builder()
                .cafeId(1L)
                .light(4.5)
                .mood(3.5)
                .price(2.5)
                .taste(1.5)
                .build();

        given(scoreSetRepository.findByCafeId(1L)).willReturn(Optional.ofNullable(mockScoreSet));

        ScoreSet scoreSet = scoreSetService.getScoreSetByCafeId(1L);
        verify(scoreSetRepository).findByCafeId(eq(1L));

        assertEquals(scoreSet.getCafeId(), 1L);
    }

    // TODO: admin-api에서 ScoreSet에 대해 어떻게 접근할 지 정해야 함
}