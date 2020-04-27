package com.cagong.caferanking.domain.entity;

import com.cagong.caferanking.domain.entity.Review;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTests {

    @Test
    public void creation() {

        Review review = Review.builder()
//                .cafeId(1L)
                .mood(3.5)
                .light(4.5)
                .price(3.5)
                .taste(1.5)
                .comment("It's so Good!")
                .build();

        assertEquals(review.getMood(), 3.5);
        assertEquals(review.getComment(), "It's so Good!");
    }
}