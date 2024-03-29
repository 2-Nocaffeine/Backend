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
    private Long imageMain;

    private Long image1;

    private Long image2;

    private Long image3;

    private Long image4;


}
