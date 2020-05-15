package com.cagong.caferanking.repository;

import com.cagong.caferanking.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByCafeId(Long cafeId);

    Optional<Review> findByCafeIdAndUserId(Long cafeId, Long userId);

    @Query(value = "SELECT avg(mood), avg(light), avg(price), avg(taste), cafe_id FROM review r WHERE r.cafe_id = ?1",
            nativeQuery = true)
    Object findAverageByCafeId(Long cafeId);

}
