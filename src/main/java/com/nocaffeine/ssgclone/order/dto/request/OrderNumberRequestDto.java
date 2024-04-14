package com.nocaffeine.ssgclone.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderNumberRequestDto {

    private Long orderId;


//    public static OrderNumberRequestDto convertToDto(OrderNumberRequestVo orderNumberRequestVo) {
//        return OrderNumberRequestDto.builder()
//                .orderId(orderNumberRequestVo.getOrderId())
//                .build();
//    }
}
