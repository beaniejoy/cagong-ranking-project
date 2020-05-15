package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.entity.Cafe;
import com.cagong.caferanking.domain.entity.CafeMenu;
import com.cagong.caferanking.domain.entity.Review;
import com.cagong.caferanking.domain.entity.ScoreSet;
import com.cagong.caferanking.domain.network.response.CafeApiResponse;
import com.cagong.caferanking.domain.network.response.CafeMenuApiResponse;
import com.cagong.caferanking.domain.network.response.ReviewApiResponse;
import com.cagong.caferanking.domain.network.response.ScoreSetApiResponse;
import com.cagong.caferanking.error.CafeNotFoundException;
import com.cagong.caferanking.page.Pagination;
import com.cagong.caferanking.repository.CafeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class CafeService {

    private CafeRepository cafeRepositoy;

    private CafeMenuService cafeMenuService;

    private ReviewService reviewService;

    private ScoreSetService scoreSetService;

    public CafeApiResponse countAll() {
        Long count = cafeRepositoy.count();

        return CafeApiResponse.builder()
                .count(count)
                .build();
    }

    public Map<String, Object> getCafes(String phrase, Pageable pageable) {
        Page<Cafe> cafes = cafeRepositoy.findAllByNameContaining(phrase, pageable);

        List<CafeApiResponse> cafeApiResponseList = cafes.stream()
                .map(this::response)
                .collect(Collectors. toList());

        Pagination pagination = Pagination.builder()
                .totalPages(cafes.getTotalPages())
                .totalElements(cafes.getTotalElements())
                .currentPage(cafes.getNumber())
                .currentElements(cafes.getNumberOfElements())
                .build();

        Map<String, Object> cafePageMap = new HashMap<>();
        cafePageMap.put("cafes", cafeApiResponseList);
        cafePageMap.put("page", pagination);

        return cafePageMap;
    }

    public CafeApiResponse getCafe(Long cafeId) {
        Cafe cafe = cafeRepositoy.findById(cafeId)
                .orElseThrow(() -> new CafeNotFoundException(cafeId));

        CafeApiResponse cafeApiResponse = response(cafe);

        // CafeMenuList
        List<CafeMenu> cafeMenuList = cafe.getCafeMenuList();
        List<CafeMenuApiResponse> cafeMenuApiResponseList = cafeMenuList.stream()
                .map(cafeMenu -> cafeMenuService.response(cafeMenu))
                .collect(Collectors.toList());
        cafeApiResponse.setCafeMenuList(cafeMenuApiResponseList);

        // ReviewList
        List<Review> reviewList = cafe.getReviewList();
        List<ReviewApiResponse> reviewApiResponseList = reviewList.stream()
                .map(review -> reviewService.response(review))
                .collect(Collectors.toList());
        cafeApiResponse.setReviewList(reviewApiResponseList);

        // ScoreSet
        ScoreSet scoreSet = cafe.getScoreSet();
        // TODO: scoreSet은 admin에서 Cafe생성할 때 자동적으로 table create하게 설정 예정,
        //  굳이 default 로 0을 설정할 필요가 없을 것이다.
        ScoreSetApiResponse scoreSetApiResponse = ScoreSetApiResponse.builder()
                .mood(.0)
                .light(.0)
                .price(.0)
                .taste(.0)
                .cafeId(cafeId)
                .build();

        if (scoreSet != null) {
            scoreSetApiResponse = scoreSetService.response(scoreSet);
        }

        cafeApiResponse.setScoreSet(scoreSetApiResponse);

        return cafeApiResponse;
    }
    public CafeApiResponse response(Cafe cafe) {

        return CafeApiResponse.builder()
                .id(cafe.getId())
                .name(cafe.getName())
                .address(cafe.getAddress())
                .imgUrl(cafe.getImgUrl())
                .phoneNumber(cafe.getPhoneNumber())
                .opertimeStart(cafe.getOpertimeStart())
                .opertimeEnd(cafe.getOpertimeEnd())
                .build();
    }

}
