package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.ReviewService;
import com.cagong.caferanking.domain.Review;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)
class ReviewControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    public void create() throws Exception {
        given(reviewService.addReview(1L,
                "Joy", 4.5, 3.5, 2.5, 1.5, "Good place")).willReturn(
                Review.builder().id(123L).build());

        mvc.perform(post("/cafes/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userName\":\"Joy\",\"mood\":4.5,\"light\":3.5,\"price\":2.5,\"taste\":1.5,\"comment\":\"Good place\"}")
        )
                .andExpect(status().isCreated())
                .andExpect(header().string(
                        "location", "/cafes/1/reviews/123"));
    }
}