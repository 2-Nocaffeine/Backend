package com.nocaffeine.ssgclone.order.dto.response;

import com.nocaffeine.ssgclone.order.vo.response.OrderStatusResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStatusResponseDto {
    private int count;

    public static OrderStatusResponseVo dtoToVo(OrderStatusResponseDto orderStatus) {
        return new OrderStatusResponseVo(orderStatus.getCount());
    }
}
