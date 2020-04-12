package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.CafeService;
import com.cagong.caferanking.domain.Cafe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@AllArgsConstructor
public class CafeController {

    private CafeService cafeService;

    @GetMapping("/cafes")
    public List<Cafe> list() {
        return cafeService.getCafes();
    }

    @GetMapping("/cafes/{cafeId}")
    public Cafe detail(@PathVariable("cafeId") Long cafeId) {
        return cafeService.getCafe(cafeId);
    }

    @GetMapping("/")
    public String view(Model model,
                       @RequestParam("cafe") Long cafeId) {
        model.addAttribute("cafe",cafeService.getCafe(cafeId));
        return "view";
    }

}