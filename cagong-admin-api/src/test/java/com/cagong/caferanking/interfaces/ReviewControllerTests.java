package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.ReviewService;
import com.cagong.caferanking.domain.Review;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReviewController.class)
class ReviewControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    public void list() throws Exception {
        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder()
                .cafeId(1L)
                .mood(3.5)
                .light(4.5)
                .price(3.5)
                .taste(1.5)
                .comment("It's so Good!")
                .build());

        given(reviewService.getReviewsByCafeId(1L)).willReturn(reviews);

        mvc.perform(get("/cafes/1/reviews"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"mood\":3.5")
                ))
                .andExpect(content().string(
                        containsString("\"comment\":\"It's so Good!\"")
                ));

    }
}