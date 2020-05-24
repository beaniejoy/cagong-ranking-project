package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.entity.Cafe;
import com.cagong.caferanking.error.CafeNotFoundException;
import com.cagong.caferanking.domain.entity.CafeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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
        // TODO: scoreSet은 자동으로 table create 설정하기
        return cafeRepositoy.save(cafe);
    }

    public Cafe updateCafe(Long cafeId, String name, String address) {
        Cafe cafe = cafeRepositoy.findById(cafeId)
                .orElseThrow(() -> new CafeNotFoundException(cafeId));


        return cafeRepositoy.save(cafe);
    }

    public void deleteCafe(Long cafeId) {
        // if Cafe to be deleted doesn't exist, throw Exception
        if(!cafeRepositoy.existsById(cafeId)){
            throw new CafeNotFoundException(cafeId);
        }

        cafeRepositoy.deleteById(cafeId);
    }
}
