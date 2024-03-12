package com.nocaffeine.ssgclone.specialprice.domain;

import com.nocaffeine.ssgclone.product.domain.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class SpecialPriceList {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private SpecialPrice specialPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Product product;
}
