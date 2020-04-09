package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.Cafe;
import com.cagong.caferanking.domain.CafeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

class CafeServiceTests {

    private CafeService cafeService;

    @Mock
    private CafeRepository cafeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        cafeService = new CafeService(cafeRepository);
    }

    @Test
    public void getCafes() {
        List<Cafe> mockCafes = new ArrayList<>();

        mockCafes.add(Cafe.builder()
                .name("Starbucks")
                .build());

        given(cafeRepository.findAll()).willReturn(mockCafes);

        List<Cafe> cafes = cafeService.getCafes();
        Cafe cafe = cafes.get(0);

        assertEquals(cafe.getName(), "Starbucks");
    }
}