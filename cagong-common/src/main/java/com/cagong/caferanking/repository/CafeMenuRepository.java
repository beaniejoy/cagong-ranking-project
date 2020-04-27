package com.cagong.caferanking.repository;

import com.cagong.caferanking.domain.entity.CafeMenu;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CafeMenuRepository extends CrudRepository<CafeMenu, Long> {
    List<CafeMenu> findAllByCafeId(Long cafeId);
}
