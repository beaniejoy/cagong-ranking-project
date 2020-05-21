package com.cagong.caferanking.page;

import com.cagong.caferanking.application.CafeService;
import com.cagong.caferanking.application.ReviewService;
import com.cagong.caferanking.domain.network.response.SessionApiResponse;
import com.cagong.caferanking.error.SessionNotAssignedException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class PageController {

    private final CafeService cafeService;

    private final ReviewService reviewService;

    // Main Page
    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("cafeCount", cafeService.countAll());
        return "main";
    }

    // Login Page
    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        if (request.getSession().getAttribute("member") != null) {
            return "member/login_handle";
        }

        return "member/login";
    }

    // Register Member Page
    @GetMapping("/regst")
    public String register(HttpServletRequest request) {
        if (request.getSession().getAttribute("member") != null) {
            return "member/login_handle";
        }

        return "member/regst";
    }

    // Search Page
    @GetMapping("/cafes/search")
    public String list(Model model,
                       @RequestParam("phrase") String phrase,
                       @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 3) Pageable pageable) {
        model.addAttribute("cafes", cafeService.getCafes(phrase, pageable).getData());
        model.addAttribute("page", cafeService.getCafes(phrase, pageable).getPagination());
        model.addAttribute("phrase", phrase);
        return "search";
    }

    // Cafe's Detail Page
    @GetMapping("/cafes/{cafeId}/detail")
    public String detail(Model model, @PathVariable("cafeId") Long cafeId) {
        model.addAttribute("cafe", cafeService.getCafe(cafeId));
        return "view/detail";
    }

    // Comment List Page in a Cafe's Detail Page
    @GetMapping("/cafes/{cafeId}/comments")
    public String list(Model model,
                       @PathVariable Long cafeId,
                       @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10) Pageable pageable) {
        model.addAttribute("comments", reviewService.getComments(cafeId, pageable).getData());
        model.addAttribute("page", reviewService.getComments(cafeId, pageable).getPagination());
        model.addAttribute("cafeId", cafeId);
        return "view/comment";
    }

    // Review Write Page
    @GetMapping("/cafes/{cafeId}/write")
    public String reviewSave(HttpServletRequest request, Model model, @PathVariable Long cafeId) {

        HttpSession session = request.getSession();
        // Login Member Inf.
        SessionApiResponse memInfo = (SessionApiResponse) session.getAttribute("member");

        if (memInfo == null) {
            throw new SessionNotAssignedException();
        }

        model.addAttribute("review",
                reviewService.getReview(cafeId, memInfo.getId()));

        model.addAttribute("cafeId", cafeId);

        return "review/write";
    }

    @GetMapping("/cafes/{cafeId}/update")
    public String reviewUpdate(HttpServletRequest request,
                               Model model,
                               @PathVariable Long cafeId) {
        return "review/review-update";
    }

}
