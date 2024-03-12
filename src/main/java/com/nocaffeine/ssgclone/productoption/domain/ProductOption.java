package com.nocaffeine.ssgclone.productoption.domain;

import com.nocaffeine.ssgclone.product.domain.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class ProductOption {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private SizeOption size;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private ColorOption color;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private AddOption otherOption;

    @NotNull
    private int quantity;
}
