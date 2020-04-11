package com.cagong.caferanking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreSet {

    @Id
    @GeneratedValue
    private Long id;

    private Long cafeId;
    private Double mood;
    private Double light;
    private Double price;
    private Double taste;

}
