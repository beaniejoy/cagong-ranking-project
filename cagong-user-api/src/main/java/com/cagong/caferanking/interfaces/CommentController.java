package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CommentController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/cafes/{cafeId}/comments")
    public String list(Model model,
                       @PathVariable Long cafeId,
                       @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10) Pageable pageable) {
        model.addAttribute("comments", reviewService.getComments(cafeId, pageable).get("comments"));
        model.addAttribute("page", reviewService.getComments(cafeId, pageable).get("page"));
        model.addAttribute("cafeId", cafeId);
        return "view/comment";
    }
}
