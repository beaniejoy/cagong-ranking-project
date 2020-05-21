package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.ReviewService;
import com.cagong.caferanking.interfaces.dto.DataWithPageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final ReviewService reviewService;

    @GetMapping("/cafes/{cafeId}/comments")
    public DataWithPageResponseDto list(
            @PathVariable Long cafeId,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10) Pageable pageable) {

        return reviewService.getComments(cafeId, pageable);
    }
}
