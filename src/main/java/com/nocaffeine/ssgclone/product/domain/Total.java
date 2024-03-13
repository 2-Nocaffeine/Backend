package com.nocaffeine.ssgclone.product.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Total {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Product product;

    @NotNull
    private int sales;

    @NotNull
    private Double review_average;

    @NotNull
    private int review_count;

}
