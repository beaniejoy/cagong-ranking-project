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

    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("cafeCount", cafeService.countAll());
        return "main";
    }

    // TODO: fastcampus 로그인/회원가입 UI처럼 통합 관리
    // 로그인 창으로 이동
    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    // 회원가입 창으로 이동
    @GetMapping("/regst")
    public String register() {
        return "member/regst";
    }

}
