package com.cagong.caferanking.domain.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class UserApiResponse {

    private Long id;

    private String account;

    private String password;

    private String phoneNumber;

    private List<ReviewApiResponse> reviewApiResponseList;
}
