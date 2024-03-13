package com.nocaffeine.ssgclone.brandstore.domain;

import com.nocaffeine.ssgclone.product.domain.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class BrandList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Brand brand;

}
