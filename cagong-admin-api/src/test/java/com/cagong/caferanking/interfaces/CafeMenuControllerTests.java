package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.CafeMenuService;
import com.cagong.caferanking.domain.entity.CafeMenu;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CafeMenuController.class)
class CafeMenuControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CafeMenuService cafeMenuService;

    @Test
    public void list() throws Exception {
        List<CafeMenu> cafeMenus = new ArrayList<>();
        cafeMenus.add(CafeMenu.builder()
                .name("americano")
                .build());

        given(cafeMenuService.getCafeMenus(1L)).willReturn(cafeMenus);

        mvc.perform(get("/cafes/1/cafemenus"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"name\":\"americano\"")));
    }

    @Test
    public void bulkUpdate() throws Exception {
        mvc.perform(patch("/cafes/1/cafemenus")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[]")
        )
                .andExpect(status().isOk());

        verify(cafeMenuService).bulkUpdate(eq(1L), any());
    }
}