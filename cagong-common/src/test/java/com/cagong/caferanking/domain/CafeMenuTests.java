package com.cagong.caferanking.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CafeMenuTests {

    @Test
    public void creation() {
        CafeMenu cafeMenu = CafeMenu.builder()
                .cafeId(1L)
                .name("Americano")
                .build();

        assertEquals(cafeMenu.getCafeId(), 1L);
        assertEquals(cafeMenu.getName(), "Americano");
        assertFalse(cafeMenu.isDestroy());
    }
}