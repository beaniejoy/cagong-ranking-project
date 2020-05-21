package com.cagong.caferanking.application;

import com.cagong.caferanking.domain.entity.CafeMenu;
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

    public void bulkUpdate(Long cafeId, List<CafeMenu> cafeMenus) {
        for (CafeMenu cafeMenu : cafeMenus) {
            if(cafeMenu.isDestroy()){
                cafeMenuRepository.deleteById(cafeMenu.getId());
                continue;
            }
            // TODO: CafeMenu Entity update 작업 필요
//            cafeMenu.setCafeId(cafeId);
            cafeMenuRepository.save(cafeMenu);
        }
    }
}
