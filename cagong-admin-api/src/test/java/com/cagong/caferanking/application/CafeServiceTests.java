package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.Cafe;
import com.cagong.caferanking.error.CafeNotFoundException;
import com.cagong.caferanking.repository.CafeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class CafeServiceTests {

    private CafeService cafeService;

    @Mock
    private CafeRepository cafeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockCafeRepository();

        cafeService = new CafeService(cafeRepository);
    }

    private void mockCafeRepository() {
        List<Cafe> cafes = new ArrayList<>();
        Cafe cafe = Cafe.builder()
                .id(1L)
                .name("Starbucks")
                .address("Busan")
                .build();
        cafes.add(cafe);

        given(cafeRepository.findAll()).willReturn(cafes);
        given(cafeRepository.findById(1L)).willReturn(Optional.ofNullable(cafe));
        given(cafeRepository.existsById(1L)).willReturn(true);
    }

    @Test
    public void getCafes() {
        List<Cafe> cafes = cafeService.getCafes();
        Cafe cafe = cafes.get(0);

        assertEquals(cafe.getName(), "Starbucks");
        assertEquals(cafe.getAddress(), "Busan");
    }

    // 존재하는 카페인 경우
    @Test
    public void getCafeWithExisted() {
        Cafe cafe = cafeService.getCafe(1L);

        assertEquals(cafe.getName(), "Starbucks");
        assertEquals(cafe.getAddress(), "Busan");
    }

    // 존재하지 않는 카페인 경우
    @Test
    public void getCafeWithNotExisted() {
        assertThrows(CafeNotFoundException.class,
                () -> cafeService.getCafe(404L));
    }

    @Test
    public void addCafe() {
        given(cafeService.addCafe(any())).will(invocation -> {
            Cafe cafe = invocation.getArgument(0);
            return Cafe.builder()
                    .id(1L)
                    .name(cafe.getName())
                    .address(cafe.getAddress())
                    .build();
        });

        Cafe cafe = Cafe.builder()
                .name("Hollys")
                .address("Seoul")
                .build();

        Cafe saved = cafeService.addCafe(cafe);

        assertEquals(saved.getName(), "Hollys");
    }

    @Test
    public void updateCafe() {
        Cafe cafe = cafeService.updateCafe(1L, "Starbucks_1", "Seoul Gwang");

        assertEquals(cafe.getName(), "Starbucks_1");
        assertEquals(cafe.getAddress(), "Seoul Gwang");
    }

    @Test
    public void deleteCafeWithExisted() {
        cafeService.deleteCafe(1L);

        verify(cafeRepository).deleteById(1L);
    }

    @Test
    public void deleteCafeWithNotExisted() {
        assertThrows(CafeNotFoundException.class,
                () -> cafeService.deleteCafe(404L));
    }

}