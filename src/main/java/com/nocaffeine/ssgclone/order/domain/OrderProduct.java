package com.nocaffeine.ssgclone.order.domain;

import com.nocaffeine.ssgclone.productoption.domain.ProductOption;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class OrderProduct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Orders order;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private ProductOption productOption;

    @NotNull
    private int price;

    @NotNull
    private int quantity;
}
