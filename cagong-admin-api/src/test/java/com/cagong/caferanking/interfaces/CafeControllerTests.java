package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.domain.CafeNotFoundException;
import com.cagong.caferanking.application.CafeService;
import com.cagong.caferanking.domain.Cafe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
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

        given(cafeService.getCafes()).willReturn(cafes);

        mvc.perform(get("/cafes"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"name\":\"Starbucks\"")))
                .andExpect(content().string(
                        containsString("\"address\":\"Busan\"")));
    }

    // 존재하는 카페인 경우
    @Test
    public void detailWithExisted() throws Exception {
        Cafe cafe = Cafe.builder()
                .id(1L)
                .name("Starbucks")
                .address("Busan")
                .build();

        given(cafeService.getCafe(1L)).willReturn(cafe);

        mvc.perform(get("/cafes/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1")))
                .andExpect(content().string(
                        containsString("\"name\":\"Starbucks\"")))
                .andExpect(content().string(
                        containsString("\"address\":\"Busan\"")));
    }

    // 존재하는 카페인 경우
    @Test
    public void detailWithNotExisted() throws Exception {
        given(cafeService.getCafe(404L))
                .willThrow(new CafeNotFoundException(404L));

        mvc.perform(get("/cafes/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }

    @Test
    public void createWithCorrectData() throws Exception {
        given(cafeService.addCafe(any())).will(invocation -> {
            Cafe cafe = invocation.getArgument(0);
            return Cafe.builder()
                    .id(2L)
                    .name(cafe.getName())
                    .address(cafe.getAddress())
                    .build();
        });

        mvc.perform(post("/cafes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Hollys\",\"address\":\"Seoul\"}")
        )
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/cafes/2"))
                .andExpect(content().string("{}"));

        verify(cafeService).addCafe(any());
    }

    @Test
    public void createWithIncorrectData() throws Exception {
        mvc.perform(post("/cafes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":,\"address\":\"Seoul\"}")
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateWithCorrectData() throws Exception {
        mvc.perform(patch("/cafes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Starbucks_1\",\"address\":\"Seoul GwangJinGu\"}")
        )
                .andExpect(status().isOk());

        verify(cafeService).updateCafe(1L, "Starbucks_1", "Seoul GwangJinGu");
    }

    @Test
    public void updateWithIncorrectData() throws Exception {
        mvc.perform(patch("/cafes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":,\"address\":\"GwangJinGu\"}")
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void removeWithExisted() throws Exception {
        mvc.perform(delete("/cafes/1"))
                .andExpect(status().isOk());

        verify(cafeService).deleteCafe(1L);
    }

}