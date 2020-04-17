package com.cagong.caferanking.repository;

import com.cagong.caferanking.domain.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findAllByCafeId(Long cafeId);

}
