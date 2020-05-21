package com.cagong.caferanking.domain.entity;

import com.cagong.caferanking.domain.entity.CafeMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CafeMenuRepository extends JpaRepository<CafeMenu, Long> {
    List<CafeMenu> findAllByCafeId(Long cafeId);
}
