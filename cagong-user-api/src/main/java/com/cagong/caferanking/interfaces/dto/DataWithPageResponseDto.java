package com.cagong.caferanking.interfaces.dto;

import com.cagong.caferanking.domain.Pagination;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataWithPageResponseDto {

    private Object data;

    private Pagination pagination;

}
