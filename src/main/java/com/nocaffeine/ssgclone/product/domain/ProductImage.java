package com.nocaffeine.ssgclone.product.domain;

import com.nocaffeine.ssgclone.common.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class ProductImage extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Image image;

    @ManyToOne
    @NotNull
    private Product product;

    @NotNull
    private boolean thumbnail;

    @NotNull
    private int imageNumber;


}
