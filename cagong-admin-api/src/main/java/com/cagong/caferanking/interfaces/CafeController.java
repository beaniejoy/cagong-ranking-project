package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.CafeService;
import com.cagong.caferanking.domain.Cafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class CafeController {

    @Autowired
    private CafeService cafeService;

    @GetMapping("/cafes")
    public List<Cafe> list() {
        return cafeService.getCafes();
    }

    @PostMapping("/cafes")
    public ResponseEntity<?> create(
            @RequestBody Cafe resource
    ) throws URISyntaxException {

        Cafe saved = cafeService.addCafe(Cafe.builder()
                .name(resource.getName())
                .build());

        String url = "/cafes/" + saved.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
