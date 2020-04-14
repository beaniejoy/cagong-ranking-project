package com.cagong.caferanking.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cafe {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

    private String imgSrc;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CafeMenu> cafeMenus;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Review> reviews;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ScoreSet scoreSet;

    public void setCafeMenus(List<CafeMenu> cafeMenus) {
        this.cafeMenus = new ArrayList<>(cafeMenus);
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = new ArrayList<>(reviews);
    }

    public void updateInformation(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
