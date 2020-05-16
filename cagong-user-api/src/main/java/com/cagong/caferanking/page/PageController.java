package com.cagong.caferanking.page;

import com.cagong.caferanking.application.CafeService;
import com.cagong.caferanking.application.ReviewService;
import com.cagong.caferanking.application.SessionNotAssignedException;
import com.cagong.caferanking.domain.network.request.SessionApiRequest;
import com.cagong.caferanking.domain.network.response.SessionApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class PageController {

    private CafeService cafeService;

    private ReviewService reviewService;

    // Main Page
    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("cafeCount", cafeService.countAll());
        return "main";
    }

    // TODO: fastcampus 로그인/회원가입 UI처럼 통합 관리
    // Login Page
    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    // Register Member Page
    @GetMapping("/regst")
    public String register() {
        return "member/regst";
    }

    // Review Write Page
    @GetMapping("/cafes/{cafeId}/write")
    public String write(HttpServletRequest request, Model model, @PathVariable Long cafeId) {

        HttpSession session = request.getSession();
        // Login Member Inf.
        SessionApiResponse memInfo = (SessionApiResponse) session.getAttribute("member");

        if(memInfo == null) {
            throw new SessionNotAssignedException();
        }

        model.addAttribute("review",
                reviewService.getReview(cafeId, memInfo.getId()));

        model.addAttribute("cafeId", cafeId);

        return "review/write";
    }

}
