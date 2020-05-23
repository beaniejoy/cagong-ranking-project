package com.cagong.caferanking.domain.network.request;

import com.cagong.caferanking.domain.entity.Cafe;
import com.cagong.caferanking.domain.entity.CafeRepository;
import com.cagong.caferanking.domain.entity.Review;
import com.cagong.caferanking.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class ReviewApiRequest {

    private Long id;

    private Double mood;

    private Double light;

    private Double price;

    private Double taste;

    private String comment;

    private Long userId;

    public Review toEntity(Cafe cafe, User user) {
        return Review.builder()
                .cafe(cafe)
                .user(user)
                .mood(mood)
                .light(light)
                .price(price)
                .taste(taste)
                .comment(comment)
                .build()
                ;
    }
}
