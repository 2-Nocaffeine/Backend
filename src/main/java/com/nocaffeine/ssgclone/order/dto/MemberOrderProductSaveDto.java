package com.nocaffeine.ssgclone.order.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberOrderProductSaveDto {

    private Long orderId;
    private Long optionSelectedProductId;
    private int price;
    private int quantity;
}
