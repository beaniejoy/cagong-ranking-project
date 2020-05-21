package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.entity.CafeMenu;
import com.cagong.caferanking.domain.network.response.CafeMenuApiResponse;
import com.cagong.caferanking.domain.entity.CafeMenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CafeMenuService {

    private CafeMenuRepository cafeMenuRepository;

    public List<CafeMenu> getCafeMenus(Long cafeId) {
        return cafeMenuRepository.findAllByCafeId(cafeId);
    }

    public CafeMenuApiResponse response(CafeMenu cafeMenu){

        return CafeMenuApiResponse.builder()
                .id(cafeMenu.getId())
                .name(cafeMenu.getName())
                .price(cafeMenu.getPrice())
                .cafeId(cafeMenu.getCafe().getId())
                .build();
    }
}
