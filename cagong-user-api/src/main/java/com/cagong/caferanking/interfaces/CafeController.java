package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.CafeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/cafes")
public class CafeController {

    private CafeService cafeService;

    @GetMapping("/search")
    public String list(Model model,
                       @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 4) Pageable pageable) {
        model.addAttribute("cafes", cafeService.getCafes(pageable).get("cafes"));
        model.addAttribute("page", cafeService.getCafes(pageable).get("page"));
        return "search";
    }

    @GetMapping("/{cafeId}")
    public String detail(Model model, @PathVariable("cafeId") Long cafeId) {
        model.addAttribute("cafe", cafeService.getCafe(cafeId));
        return "view";
    }

}