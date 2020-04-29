package com.cagong.caferanking.page;

import com.cagong.caferanking.application.CafeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class PageController {

    private CafeService cafeService;

    @GetMapping("/main")
    public String index(Model model) {
        model.addAttribute("cafeCount", cafeService.countAll());
        return "main";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "member/login";
    }

    @GetMapping("/regst")
    public String register(Model model) {
        return "member/regst";
    }

}
