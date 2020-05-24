package com.cagong.caferanking.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"cafe"})
@Builder
@Entity
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

    public void updateAverage(Object changed) {
        Object[] avgResultSet = (Object[]) changed;
        this.mood = (Double) avgResultSet[0];
        this.light = (Double) avgResultSet[1];
        this.price = (Double) avgResultSet[2];
        this.taste = (Double) avgResultSet[3];
    }
}
