package com.cagong.caferanking.interfaces;

import com.cagong.caferanking.application.CafeService;
import com.cagong.caferanking.domain.Cafe;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
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

    @PatchMapping("/cafes/{cafeId}")
    public String update(@PathVariable("cafeId") Long cafeId,
                         @Valid @RequestBody Cafe resource) {
        String name = resource.getName();
        String address = resource.getAddress();
        cafeService.updateCafe(cafeId, name, address);

        return "{}";
    }

    @DeleteMapping("/cafes/{cafeId}")
    public String remove(@PathVariable("cafeId") Long cafeId) {
        cafeService.deleteCafe(cafeId);
        return "{}";
    }

}