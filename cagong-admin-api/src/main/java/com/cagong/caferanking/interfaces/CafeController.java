package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.CafeService;
import com.cagong.caferanking.domain.Cafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/cafes/{cafeId}")
    public Cafe detail(@PathVariable("cafeId") Long cafeId) {
        return cafeService.getCafe(cafeId);
    }

    @PostMapping("/cafes")
    public ResponseEntity<?> create(
            @Valid @RequestBody Cafe resource
    ) throws URISyntaxException {

        Cafe cafe = cafeService.addCafe(Cafe.builder()
                .name(resource.getName())
                .address(resource.getAddress())
                .build());

        String url = "/cafes/" + cafe.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

}
