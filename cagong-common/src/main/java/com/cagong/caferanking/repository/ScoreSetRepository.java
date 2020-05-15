package com.cagong.caferanking.repository;

import com.cagong.caferanking.domain.entity.ScoreSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScoreSetRepository extends JpaRepository<ScoreSet, Long> {
    Optional<ScoreSet> findByCafeId(Long cafeId);

    List<ScoreSet> findTop5ByOrderByMoodDesc();

    List<ScoreSet> findTop5ByOrderByLightDesc();

    List<ScoreSet> findTop5ByOrderByPriceDesc();

    List<ScoreSet> findTop5ByOrderByTasteDesc();

}
