package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.ReviewService;
import com.cagong.caferanking.domain.network.request.ReviewApiRequest;
import com.cagong.caferanking.domain.network.response.ReviewApiResponse;
import com.cagong.caferanking.domain.network.response.SessionApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping(value = "/cafes/{cafeId}/reviews")
    public String create(
            HttpServletRequest request,
            @PathVariable("cafeId") Long cafeId,
            @RequestBody ReviewApiRequest resource)
            throws URISyntaxException {

        // TODO: token을 이용한 userName 받아오기
        HttpSession session = request.getSession();
        SessionApiResponse memInfo = (SessionApiResponse) session.getAttribute("member");

        reviewService.addReview(
                cafeId,
                memInfo.getId(),
                resource.getMood(), resource.getLight(), resource.getPrice(), resource.getTaste(),
                resource.getComment());


        return "redirect:/cafes/" + cafeId;
    }

    @PutMapping("/cafes/{cafeId}/reviews")
    public ReviewApiResponse updateReview(@PathVariable Long cafeId) {
        return null;
    }

}
