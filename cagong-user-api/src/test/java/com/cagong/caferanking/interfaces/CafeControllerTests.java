package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.CafeService;
import com.cagong.caferanking.domain.network.response.CafeApiResponse;
import com.cagong.caferanking.domain.network.response.CafeMenuApiResponse;
import com.cagong.caferanking.domain.network.response.ReviewApiResponse;
import com.cagong.caferanking.domain.network.response.ScoreSetApiResponse;
import com.cagong.caferanking.error.CafeNotFoundException;
import com.cagong.caferanking.interfaces.dto.DataWithPageResponseDto;
import com.cagong.caferanking.domain.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CafeController.class)
public class CafeControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CafeService cafeService;

    @Test
    public void 카페_리스트_조회() throws Exception {

        List<CafeApiResponse> cafeApiResponseList = new ArrayList<>();

        cafeApiResponseList.add(CafeApiResponse.builder()
                .id(1L)
                .name("스타벅스 1호점")
                .address("을지로")
                .imgUrl("/img/cafe1.jpg")
                .opertimeStart(LocalTime.now())
                .opertimeEnd(LocalTime.now())
                .phoneNumber("010-2222-1111")
                .build()
        );

        Pagination pagination = Pagination.builder()
                .currentElements(1)
                .currentPage(1)
                .totalElements(1L)
                .totalPages(1)
                .build();

        Pageable pageable = PageRequest.of(0, 3, Sort.Direction.ASC, "id");

        DataWithPageResponseDto cafePageDto = DataWithPageResponseDto.builder()
                .data(cafeApiResponseList)
                .pagination(pagination)
                .build();

        given(cafeService.getCafes("스타벅스", pageable)).willReturn(cafePageDto);

        mvc.perform(get("/cafes")
                .param("phrase", "스타벅스"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(
                        jsonPath("$.data[0].id", is(1))
                );
        ;
    }

    @Test
    public void 카페_상세정보_조회_Found() throws Exception {

        CafeApiResponse cafeApiResponse = CafeApiResponse.builder()
                .id(1L)
                .name("스타벅스 1호점")
                .address("을지로")
                .imgUrl("/img/cafe1.jpg")
                .opertimeStart(LocalTime.now())
                .opertimeEnd(LocalTime.now())
                .phoneNumber("010-2222-1111")
                .build();

        List<CafeMenuApiResponse> cafeMenuApiResponseList = new ArrayList<>();
        cafeMenuApiResponseList.add(CafeMenuApiResponse.builder()
                .id(100L)
                .name("아메리카노")
                .price(6000)
                .cafeId(1L)
                .build());

        cafeApiResponse.setCafeMenuList(cafeMenuApiResponseList);

//        List<ReviewApiResponse> reviewApiResponseList = new ArrayList<>();
//        reviewApiResponseList.add(ReviewApiResponse.builder()
//                .id(1000L)
//                .cafeName("스타벅스 1호점")
//                .userName("beaniejoy")
//                .mood(4.5)
//                .light(3.5)
//                .price(1.5)
//                .taste(2.5)
//                .comment("맛있다!")
//                .build())
//        ;
//
//        cafeApiResponse.setReviewList(reviewApiResponseList);

        cafeApiResponse.setScoreSet(ScoreSetApiResponse.builder()
                .id(50L)
                .cafeName("스타벅스 1호점")
                .cafeId(1L)
                .mood(2.3)
                .light(1.7)
                .price(4.23)
                .taste(2.467)
                .build())
        ;

        given(cafeService.getCafe(1L)).willReturn(cafeApiResponse);

        mvc.perform(get("/cafes/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(
                        jsonPath("$.cafeMenuList[0].cafeId", is(1))
                );
    }

    @Test
    public void 카페_상세정보_조회_NotFound() throws Exception {
        given(cafeService.getCafe(404L))
                .willThrow(new CafeNotFoundException(404L));

        mvc.perform(get("/cafes/404"))
                .andExpect(status().isNotFound());
    }
}