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
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CafeMenu {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Long cafeId;

    @NotNull
    private String name;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private boolean destroy;
}
