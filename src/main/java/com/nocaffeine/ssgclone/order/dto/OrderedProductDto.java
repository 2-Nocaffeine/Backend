package com.nocaffeine.ssgclone.order.dto;

import com.nocaffeine.ssgclone.order.vo.request.OrderedProductRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderedProductDto {

    private Long optionSelectedProductId;
    private int count;
    private int price;

    public static OrderedProductDto convertToDto(OrderedProductRequestVo orderProducts) {

        return OrderedProductDto.builder()
                .optionSelectedProductId(orderProducts.getOptionSelectedProductId())
                .count(orderProducts.getCount())
                .price(orderProducts.getPrice())
                .build();
    }
}
