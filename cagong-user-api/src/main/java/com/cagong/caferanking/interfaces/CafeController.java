package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.CafeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/cafes")
public class CafeController {

    private CafeService cafeService;

    @GetMapping("/search")
    public String list(Model model,
                       @RequestParam("phrase") String phrase,
                       @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 3) Pageable pageable) {
        model.addAttribute("cafes", cafeService.getCafes(phrase, pageable).get("cafes"));
        model.addAttribute("page", cafeService.getCafes(phrase, pageable).get("page"));
        model.addAttribute("phrase", phrase);
        return "search";
    }

    @GetMapping("/{cafeId}")
    public String detail(Model model, @PathVariable("cafeId") Long cafeId) {
        model.addAttribute("cafe", cafeService.getCafe(cafeId));
        return "view";
    }

}