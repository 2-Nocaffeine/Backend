package com.nocaffeine.ssgclone.product.domain;

import com.nocaffeine.ssgclone.common.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class ProductImage{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Image image;

    @ManyToOne
    @NotNull
    private Product product;

    @NotNull
    private long imageMain;

    private long image1;

    private long image2;

    private long image3;

    private long image4;


}
