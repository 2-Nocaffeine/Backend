package com.nocaffeine.ssgclone.product.domain;

import com.nocaffeine.ssgclone.common.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
public class Product extends BaseTimeEntity {

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
