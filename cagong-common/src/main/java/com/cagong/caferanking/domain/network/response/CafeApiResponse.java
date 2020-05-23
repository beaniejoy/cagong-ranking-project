package com.cagong.caferanking.domain.network.response;

import com.cagong.caferanking.domain.entity.ScoreSet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class CafeApiResponse {

    private Long id;

    private String name;

    private String address;

    private String imgUrl;

    private LocalTime opertimeStart;

    private LocalTime opertimeEnd;

    private String phoneNumber;

    private List<CafeMenuApiResponse> cafeMenuList;

    private ScoreSetApiResponse scoreSet;

    private Long count;

    // member session 정보 있을 경우에 카페에 대한 review 작성 여부 조회
    private Boolean isWrite;

    public void setIsWriteToFalse() {
        this.isWrite = false;
    }

    public void setIsWriteToTrue() {
        this.isWrite = true;
    }
}
