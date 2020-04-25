package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.*;
import com.cagong.caferanking.error.CafeNotFoundException;
import com.cagong.caferanking.network.Pagination;
import com.cagong.caferanking.network.response.CafeApiResponse;
import com.cagong.caferanking.repository.CafeMenuRepository;
import com.cagong.caferanking.repository.CafeRepository;
import com.cagong.caferanking.repository.ReviewRepository;
import com.cagong.caferanking.repository.ScoreSetRepository;
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
    private CafeMenuRepository cafeMenuRepository;
    private ReviewRepository reviewRepository;
    private ScoreSetRepository scoreSetRepository;

    public Map<String, Object> getCafes(Pageable pageable) {
        Page<Cafe> cafes = cafeRepositoy.findAll(pageable);

        List<CafeApiResponse> cafeApiResponseList = cafes.stream()
                .map(cafe -> {
                    CafeApiResponse cafeApiResponse = CafeApiResponse.builder()
                            .id(cafe.getId())
                            .name(cafe.getName())
                            .imgUrl(cafe.getImgUrl())
                            .build();

                    return cafeApiResponse;
                })
                .collect(Collectors.toList());

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

    public Cafe getCafe(Long cafeId) {

        return cafeRepositoy.findById(cafeId)
                .orElseThrow(() -> new CafeNotFoundException(cafeId));
    }

}
