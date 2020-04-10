package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.Cafe;
import com.cagong.caferanking.domain.CafeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CafeService {

    private CafeRepository cafeRepositoy;

    public List<Cafe> getCafes() {
        return cafeRepositoy.findAll();
    }

    public Cafe getCafe(Long cafeId) {
        return cafeRepositoy.findById(cafeId)
                .orElseThrow(() -> new CafeNotFoundException(cafeId));
    }

    public Cafe addCafe(Cafe cafe) {
        return cafeRepositoy.save(cafe);
    }
}
