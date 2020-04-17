package com.cagong.caferanking.repository;

import com.cagong.caferanking.domain.Cafe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CafeRepository extends CrudRepository<Cafe, Long> {
    List<Cafe> findAll();

    @Override
    Optional<Cafe> findById(Long id);
}
