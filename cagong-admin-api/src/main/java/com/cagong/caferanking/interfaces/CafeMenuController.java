package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.CafeMenuService;
import com.cagong.caferanking.domain.CafeMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CafeMenuController {

    @Autowired
    private CafeMenuService cafeMenuService;

    @GetMapping("/cafes/{cafeId}/cafemenus")
    public List<CafeMenu> list(@PathVariable("cafeId") Long cafeId) {
        return cafeMenuService.getCafeMenus(cafeId);
    }

    @PatchMapping("/cafes/{cafeId}/cafemenus")
    public String bulkUpdate(@PathVariable("cafeId") Long cafeId,
                             @RequestBody List<CafeMenu> cafeMenus) {
        cafeMenuService.bulkUpdate(cafeId, cafeMenus);
        return "{}";
    }
}
