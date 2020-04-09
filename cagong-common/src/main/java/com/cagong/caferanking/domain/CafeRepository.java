package com.cagong.caferanking.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CafeRepository extends CrudRepository<Cafe, Long> {
    List<Cafe> findAll();
}
