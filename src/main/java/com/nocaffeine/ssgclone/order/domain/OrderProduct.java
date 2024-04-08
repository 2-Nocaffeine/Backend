package com.nocaffeine.ssgclone.order.domain;

import com.nocaffeine.ssgclone.order.dto.request.OrderedProductRequestDto;
import com.nocaffeine.ssgclone.product.domain.OptionSelectedProduct;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Orders order;

    @NotNull
    private Long productId;

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


    @Builder
    public OrderProduct(Long id,Orders order, Long productId, String productName, int price, int quantity, String thumbnailUrl, String color, String size, String addOption, String brand) {
        this.id = id;
        this.order = order;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.thumbnailUrl = thumbnailUrl;
        this.color = color;
        this.size = size;
        this.addOption = addOption;
        this.brand = brand;
    }
}
