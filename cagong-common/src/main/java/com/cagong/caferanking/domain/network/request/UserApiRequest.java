package com.cagong.caferanking.domain.network.request;

import com.cagong.caferanking.domain.network.response.ReviewApiResponse;
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
public class UserApiRequest {

    private Long id;

    private String account;

    private String email;

    private String phoneNumber;

    private String password;
    
    private List<ReviewApiResponse> reviewApiResponseList;
}
