package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.entity.Cafe;
import com.cagong.caferanking.error.CafeNotFoundException;
import com.cagong.caferanking.domain.entity.CafeRepository;
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
//        mockAllRepository();
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

//    private void mockAllRepository() {
//        List<CafeMenu> cafeMenus = new ArrayList<>();
//        cafeMenus.add(CafeMenu.builder()
////                .cafeId(1L)
//                .name("Americano")
//                .build());
//        given(cafeMenuRepository.findAllByCafeId(1L)).willReturn(cafeMenus);
//
//        List<Review> reviews = new ArrayList<>();
//        reviews.add(Review.builder()
////                .cafeId(1L)
//                .userName("Joy")
//                .mood(3.5)
//                .light(4.5)
//                .price(3.5)
//                .taste(1.5)
//                .comment("It's so Good!")
//                .build());
//        given(reviewRepository.findAllByCafeId(1L)).willReturn(reviews);
//
//        ScoreSet scoreSet = ScoreSet.builder()
////                .cafeId(1L)
//                .light(4.5)
//                .mood(3.5)
//                .price(2.5)
//                .taste(1.5)
//                .build();
//        given(scoreSetRepository.findByCafeId(1L)).willReturn(Optional.ofNullable(scoreSet));
//    }

//    @Test
//    public void getCafes() {
//        List<Cafe> cafes = cafeService.getCafes();
//        Cafe cafe = cafes.get(0);
//
//        assertEquals(cafe.getName(), "Starbucks");
//        assertEquals(cafe.getAddress(), "Busan");
//    }

    // 존재하는 카페인 경우
//    @Test
//    public void getCafeWithExisted() {
//        Cafe cafe = cafeService.getCafe(1L);
//
//        assertEquals(cafe.getName(), "Starbucks");
//        assertEquals(cafe.getAddress(), "Busan");
//
//        CafeMenu cafeMenu = cafe.getCafeMenus().get(0);
//        assertEquals(cafeMenu.getName(), "Americano");
//
//        Review review = cafe.getReviews().get(0);
//        assertEquals(review.getUserName(), "Joy");
//
//        ScoreSet scoreSet = cafe.getScoreSet();
//        assertEquals(scoreSet.getLight(), 4.5);
//    }

    // 존재하지 않는 카페인 경우
    @Test
    public void getCafeWithNotExisted() {
        assertThrows(CafeNotFoundException.class,
                () -> cafeService.getCafe(404L));
    }

}