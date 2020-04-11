package com.cagong.caferanking.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ScoreSetRepository extends CrudRepository<ScoreSet, Long> {

    Optional<ScoreSet> findByCafeId(Long cafeId);

    ;
}
