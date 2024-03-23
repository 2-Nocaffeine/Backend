package com.nocaffeine.ssgclone.product.domain;

import com.nocaffeine.ssgclone.common.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor // 기본 생성자를 생성해준다.
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 255)
    private String name;

    @NotNull
    private int price;

    @NotNull
    @Column(length = 10000)
    private String content;

    @NotNull
    private int discount;

    @Builder
    public Product(String name, int price, String content, int discount) {
        this.name = name;
        this.price = price;
        this.content = content;
        this.discount = discount;
    }
}
