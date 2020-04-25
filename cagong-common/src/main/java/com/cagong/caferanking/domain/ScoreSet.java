package com.cagong.caferanking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"cafe"})
public class ScoreSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double mood;

    private Double light;

    private Double price;

    private Double taste;

    @JsonIgnore
    @OneToOne
    private Cafe cafe;
}
