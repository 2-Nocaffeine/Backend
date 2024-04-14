package com.nocaffeine.ssgclone.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductListResponseDto {

    private String productName;
    private Long productId;
    private String addOption;
    private String color;
    private String size;
    private int count;
    private int price;
    private String brand;
    private String thumbnail;
    private String orderPhoneNumber;
    private String region;


}
