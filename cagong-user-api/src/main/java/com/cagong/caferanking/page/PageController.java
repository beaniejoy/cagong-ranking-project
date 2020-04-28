package com.cagong.caferanking.page;

import com.cagong.caferanking.application.CafeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
@AllArgsConstructor
public class PageController {

    private CafeService cafeService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("cafeCount", cafeService.countAll());
        return "main";
    }

}
