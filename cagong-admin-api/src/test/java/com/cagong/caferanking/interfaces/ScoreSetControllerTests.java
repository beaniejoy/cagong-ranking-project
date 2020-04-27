package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.ScoreSetService;
import com.cagong.caferanking.domain.entity.ScoreSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ScoreSetController.class)
class ScoreSetControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ScoreSetService scoreSetService;

    @Test
    public void getScoreSet() throws Exception {
        ScoreSet scoreSet = ScoreSet.builder()
//                .cafeId(1L)
                .light(4.5)
                .mood(3.5)
                .price(2.5)
                .taste(1.5)
                .build();

        given(scoreSetService.getScoreSetByCafeId(1L)).willReturn(scoreSet);

        mvc.perform(get("/cafes/1/scoreset"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"cafeId\":1")
                ))
                .andExpect(content().string(
                        containsString("\"light\":4.5")
                ));

    }
}