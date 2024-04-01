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

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private OptionSelectedProduct optionSelectedProduct;

    @NotNull
    private int price;

    @NotNull
    private int quantity;

    public static OrderProduct toEntity(Orders savedOrders, OptionSelectedProduct optionSelectedProduct, OrderedProductRequestDto orderedProductRequestDto){
        return OrderProduct.builder()
                .order(savedOrders)
                .optionSelectedProduct(optionSelectedProduct)
                .price(orderedProductRequestDto.getPrice())
                .quantity(orderedProductRequestDto.getCount())
                .build();
    }


}
