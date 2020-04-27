package com.cagong.caferanking.domain.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = {"cafeMenuList", "reviewList"})
public class Cafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    private String address;

    private String imgUrl;

    private LocalTime opertimeStart;

    private LocalTime opertimeEnd;

    private String phoneNumber;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    // Cafe : CafeMenu = 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cafe")
    private List<CafeMenu> cafeMenuList;

    // Cafe : Review = 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cafe")
    private List<Review> reviewList;

    // Cafe : ScoreSet = 1 : 1
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "cafe")
    private ScoreSet scoreSet;
}
