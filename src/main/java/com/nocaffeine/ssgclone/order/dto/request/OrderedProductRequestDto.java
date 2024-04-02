package com.nocaffeine.ssgclone.order.dto.request;

import com.nocaffeine.ssgclone.order.vo.request.OrderedProductRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderedProductRequestDto {

    private Long optionSelectedProductId;
    private int count;
    private int price;
    private Long thumbnailId;

    public static OrderedProductRequestDto convertToDto(OrderedProductRequestVo orderProducts) {

        return OrderedProductRequestDto.builder()
                .optionSelectedProductId(orderProducts.getOptionSelectedProductId())
                .count(orderProducts.getCount())
                .price(orderProducts.getPrice())
                .thumbnailId(orderProducts.getThumbnailId())
                .build();
    }
}
