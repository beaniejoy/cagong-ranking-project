package com.cagong.caferanking.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CafeRepository extends CrudRepository<Cafe, Long> {
    List<Cafe> findAll();

    @Override
    Optional<Cafe> findById(Long cafeId);
}
