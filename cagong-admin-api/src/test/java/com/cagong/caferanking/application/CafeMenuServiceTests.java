package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.CafeMenu;
import com.cagong.caferanking.domain.CafeMenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CafeMenuServiceTests {

    private CafeMenuService cafeMenuService;

    @Mock
    private CafeMenuRepository cafeMenuRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cafeMenuService = new CafeMenuService(cafeMenuRepository);
    }

    @Test
    public void getCafeMenus() {
        List<CafeMenu> mockCafeMenus = new ArrayList<>();
        mockCafeMenus.add(CafeMenu.builder()
                .cafeId(1L)
                .name("americano")
                .build());

        given(cafeMenuRepository.findAllByCafeId(1L)).willReturn(mockCafeMenus);

        List<CafeMenu> cafeMenus = cafeMenuService.getCafeMenus(1L);
        CafeMenu cafeMenu = cafeMenus.get(0);

        assertEquals(cafeMenu.getName(), "americano");
    }

    @Test
    public void bulkUpdate() {
        List<CafeMenu> cafeMenus = new ArrayList<>();
        cafeMenus.add(CafeMenu.builder().name("Cafe Latte").build());
        cafeMenus.add(CafeMenu.builder().id(310L).name("Orange Juice").build());
        cafeMenus.add(CafeMenu.builder().id(1004L).destroy(true).build());

        cafeMenuService.bulkUpdate(1L, cafeMenus);

        verify(cafeMenuRepository, times(2)).save(any());
    }

}