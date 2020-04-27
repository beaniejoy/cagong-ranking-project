package com.cagong.caferanking.domain.entity;

import com.cagong.caferanking.domain.entity.Cafe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CafeTests {

    @Test
    public void creation() {

        Cafe cafe = Cafe.builder()
                .name("Starbucks")
                .address("Seoul")
                .build();

        assertEquals(cafe.getName(), "Starbucks");
        assertEquals(cafe.getAddress(), "Seoul");
    }


}