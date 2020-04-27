package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.CafeService;
import com.cagong.caferanking.domain.entity.Cafe;
import com.cagong.caferanking.error.CafeNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CafeController.class)
class CafeControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CafeService cafeService;

    // 기존 Cafe - id: 1, name: Starbucks, address: Busan
    // 추가 Cafe - id: 2, name: Hollys, address: Seoul

    @Test
    public void list() throws Exception {
        List<Cafe> cafes = new ArrayList<>();
        cafes.add(Cafe.builder()
                .name("Starbucks")
                .address("Busan")
                .build());

//        given(cafeService.getCafes()).willReturn(cafes);
//
//        mvc.perform(get("/cafes"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(
//                        containsString("\"name\":\"Starbucks\"")))
//                .andExpect(content().string(
//                        containsString("\"address\":\"Busan\"")));
    }

    // 존재하는 카페인 경우
//    @Test
//    public void detailWithExisted() throws Exception {
//        Cafe cafe = Cafe.builder()
//                .id(1L)
//                .name("Starbucks")
//                .address("Busan")
//                .build();
//
//        CafeMenu cafeMenu = CafeMenu.builder()
//                .cafeId(1L)
//                .name("Americano")
//                .build();
//
//        Review review = Review.builder()
//                .cafeId(1L)
//                .userName("Joy")
//                .mood(3.5)
//                .light(4.5)
//                .price(3.5)
//                .taste(1.5)
//                .comment("It's so Good!")
//                .build();
//
//        ScoreSet scoreSet = ScoreSet.builder()
//                .cafeId(1L)
//                .light(4.5)
//                .mood(3.5)
//                .price(2.5)
//                .taste(1.5)
//                .build();
//
//        cafe.setCafeMenus(Collections.singletonList(cafeMenu));
//        cafe.setReviews(Collections.singletonList(review));
//        cafe.setScoreSet(scoreSet);
//
//        given(cafeService.getCafe(1L)).willReturn(cafe);
//
//        mvc.perform(get("/cafes/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(
//                        containsString("\"id\":1")))
//                .andExpect(content().string(
//                        containsString("\"name\":\"Starbucks\"")))
//                .andExpect(content().string(
//                        containsString("\"address\":\"Busan\"")))
//                .andExpect(content().string(
//                        containsString("\"name\":\"Americano\"")))
//                .andExpect(content().string(
//                        containsString("\"comment\":\"It's so Good!\"")));
//    }

    // 존재하는 카페인 경우
    @Test
    public void detailWithNotExisted() throws Exception {
        given(cafeService.getCafe(404L))
                .willThrow(new CafeNotFoundException(404L));

        mvc.perform(get("/cafes/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }

}