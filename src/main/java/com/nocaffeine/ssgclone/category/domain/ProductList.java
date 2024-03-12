package com.nocaffeine.ssgclone.category.domain;

import com.nocaffeine.ssgclone.product.domain.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class ProductList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private LargeCategory largeCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private MediumCategory mediumCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private SmallCategory smallCategory;

    private Long tinyCategory;

}
