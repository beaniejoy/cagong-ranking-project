package com.cagong.caferanking.repository;

import com.cagong.caferanking.CaferankingCommonApplicationTests;
import com.cagong.caferanking.domain.entity.Cafe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CafeRepositoryTests extends CaferankingCommonApplicationTests {

    @Autowired
    private CafeRepository cafeRepository;

    @Test
    @Transactional
    public void read() {
        Optional<Cafe> cafe = cafeRepository.findById(2L);

        cafe.ifPresent(selectCafe -> {
            selectCafe.getCafeMenuList().stream().forEach(cafeMenu -> {
                System.out.println("카페메뉴 이름 : " + cafeMenu.getName()
                        + "/ 가격 : " + cafeMenu.getPrice());
            });
            selectCafe.getReviewList().stream().forEach(review -> {
                System.out.println("리뷰 작성자 : " + review.getUser().getAccount());
                System.out.println("리뷰 내용 : " + review.getComment());
            });
//            System.out.println("카페 총 평가 : " + selectCafe.getScoreSet().getLight());
                assertNull(selectCafe.getScoreSet());
        });
        assertNotNull(cafe);
    }
}