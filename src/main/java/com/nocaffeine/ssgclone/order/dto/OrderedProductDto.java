package com.nocaffeine.ssgclone.order.dto;

import com.nocaffeine.ssgclone.order.vo.request.OrderedProductVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderedProductDto {

    private Long optionSelectedProductId;
    private int count;
    private int price;

    public static OrderedProductDto convertToDto(OrderedProductVo orderProducts) {

        return OrderedProductDto.builder()
                .optionSelectedProductId(orderProducts.getOptionSelectedProductId())
                .count(orderProducts.getCount())
                .price(orderProducts.getPrice())
                .build();
    }
}
