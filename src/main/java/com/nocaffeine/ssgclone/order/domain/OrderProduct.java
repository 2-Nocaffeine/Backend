package com.nocaffeine.ssgclone.order.domain;

import com.nocaffeine.ssgclone.order.dto.request.OrderedProductRequestDto;
import com.nocaffeine.ssgclone.product.domain.OptionSelectedProduct;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Orders order;

    @NotNull
    @Column(length = 255)
    private String productName;

    @NotNull
    private int price;

    @NotNull
    private int quantity;

    @NotNull
    @Column(length = 255)
    private String thumbnailUrl;

    @NotNull
    @Column(length = 50)
    private String color;

    @NotNull
    @Column(length = 50)
    private String size;

    @NotNull
    @Column(length = 50)
    private String addOption;

    @NotNull
    @Column(length = 50)
    private String brand;


}
