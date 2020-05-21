package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.CafeService;
import com.cagong.caferanking.domain.network.response.CafeApiResponse;
import com.cagong.caferanking.interfaces.dto.DataWithPageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/cafes")
@RequiredArgsConstructor
@RestController
public class CafeController {

    private final CafeService cafeService;

    @GetMapping(value = "", produces = "application/json;charset=UTF-8")
    public DataWithPageResponseDto list(@RequestParam("phrase") String phrase,
                                        @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 3) Pageable pageable) {

        return cafeService.getCafes(phrase, pageable);
    }

    @GetMapping(value = "/{cafeId}", produces = "application/json;charset=UTF-8")
    public CafeApiResponse detail(@PathVariable("cafeId") Long cafeId) {
        return cafeService.getCafe(cafeId);
    }

}