package com.cagong.caferanking.domain.network.request;

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

}
