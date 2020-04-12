package com.cagong.caferanking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    private Long cafeId;

    // TODO: token으로 받아서 사용할 것
    private String userName;

    private Double mood;
    private Double light;
    private Double price;
    private Double taste;

    @NotEmpty
    private String comment;
}
