package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.Cafe;
import com.cagong.caferanking.domain.CafeNotFoundException;
import com.cagong.caferanking.domain.CafeRepository;
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
        return cafeRepositoy.save(cafe);
    }

    public Cafe updateCafe(Long cafeId, String name, String address) {
        Cafe cafe = cafeRepositoy.findById(cafeId)
                .orElseThrow(() -> new CafeNotFoundException(cafeId));

        cafe.updateInformation(name, address);

        return cafe;
    }

    public void deleteCafe(Long cafeId) {
        // if Cafe to be deleted doesn't exist, throw Exception
        if(!cafeRepositoy.existsById(cafeId)){
            throw new CafeNotFoundException(cafeId);
        }

        cafeRepositoy.deleteById(cafeId);
    }
}
