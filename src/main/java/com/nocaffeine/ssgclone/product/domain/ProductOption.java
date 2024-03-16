package com.nocaffeine.ssgclone.product.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
public class ProductOption {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private SizeOption sizeOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private ColorOption colorOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private AddOption addOption;

    @NotNull
    private int quantity;
}
