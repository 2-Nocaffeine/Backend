package com.nocaffeine.ssgclone.product.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Total {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Product product;

    @NotNull
    private int sales;

    @NotNull
    @Column(name = "rate_average")
    private Double rateAverage;

    @NotNull
    @Column(name = "review_count")
    private int reviewCount;

    @Builder
    public Total(Long id, Product product, int sales, Double rateAverage, int reviewCount) {
        this.id = id;
        this.product = product;
        this.sales = sales;
        this.rateAverage = rateAverage;
        this.reviewCount = reviewCount;
    }
}
