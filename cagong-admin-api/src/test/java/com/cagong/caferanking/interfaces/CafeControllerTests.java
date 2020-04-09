package com.cagong.caferanking.interfaces;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CafeController.class)
class CafeControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CafeService cafeService;

    @Test
    public void list() throws Exception {
        List<Cafe> cafes = new ArrayList<>();
        cafes.add(Cafe.builder()
                .name("Starbucks")
                .build());

        given(cafeService.getCafes()).willReturn(cafes);

        mvc.perform(get("/cafes"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"name\":\"Starbucks\"")
                ));
    }

    @Test
    public void create() throws Exception {
        mvc.perform(post("/cafes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Hollys\"}")
        )
                .andExpect(status().isCreated());

        verify(cafeService).addCafe(any());
    }

}