package com.cagong.caferanking.domain.network.response;

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
public class ReviewApiResponse {

    private Long id;

    // TODO: jwt관련해서 어떻게 처리할 것인지 고민하기
    private String userName;

    private String cafeName;

    private Double mood;

    private Double light;

    private Double price;

    private Double taste;

    private String comment;
}
