package com.nocaffeine.ssgclone.product.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private int price;

    @NotNull
    private String content;

    @NotNull
    private int discount;

}
