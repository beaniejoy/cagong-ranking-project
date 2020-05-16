package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.ReviewService;
import com.cagong.caferanking.domain.network.request.ReviewApiRequest;
import com.cagong.caferanking.domain.network.response.SessionApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URISyntaxException;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/cafes/{cafeId}/reviews")
    public String create(
            HttpServletRequest request,
            @PathVariable("cafeId") Long cafeId,
            ReviewApiRequest resource)
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

}
