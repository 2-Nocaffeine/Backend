package com.nocaffeine.ssgclone.order.dto;

import com.nocaffeine.ssgclone.order.vo.request.OrderIdRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderIdDto {

    private Long orderId;

    public static OrderIdDto convertToDto(OrderIdRequestVo orderIdRequestVo){
        return OrderIdDto.builder()
                .orderId(orderIdRequestVo.getOrderId())
                .build();
    }
}
