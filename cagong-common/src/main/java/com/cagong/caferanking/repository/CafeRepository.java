package com.cagong.caferanking.repository;

import com.cagong.caferanking.domain.entity.Cafe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe, Long> {
    @Override
    Optional<Cafe> findById(Long id);

    Page<Cafe> findAllByNameContaining(String phrase, Pageable pageable);

    List<Cafe> findAll();

    Page<Cafe> findAll(Pageable pageable);

}
