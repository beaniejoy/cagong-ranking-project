package com.cagong.caferanking.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CafeMenuRepository extends CrudRepository<CafeMenu, Long> {
    List<CafeMenu> findAllByCafeId(Long cafeId);
}
