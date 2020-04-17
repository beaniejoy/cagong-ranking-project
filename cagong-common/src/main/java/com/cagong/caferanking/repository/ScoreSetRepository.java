package com.cagong.caferanking.repository;

import com.cagong.caferanking.domain.ScoreSet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ScoreSetRepository extends CrudRepository<ScoreSet, Long> {
    Optional<ScoreSet> findByCafeId(Long cafeId);
}
