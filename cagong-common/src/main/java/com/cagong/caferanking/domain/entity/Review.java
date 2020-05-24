package com.cagong.caferanking.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"cafe", "user"})
@Entity
public class Review extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double mood;

    private Double light;

    private Double price;

    private Double taste;

    @NotEmpty
    private String comment;

    // Review : Cafe = N : 1
    @JsonIgnore
    @ManyToOne
    private Cafe cafe;

    // Review : User = N : 1
    @JsonIgnore
    @ManyToOne
    private User user;

    public void updateReview(Double mood, Double light, Double price, Double taste, String comment) {
        this.mood = mood;
        this.light = light;
        this.price = price;
        this.taste = taste;
        this.comment = comment;
    }
}
