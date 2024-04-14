package com.nocaffeine.ssgclone.order.vo.response;

import com.nocaffeine.ssgclone.order.dto.response.OrderNameAndOrderIdResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class OrderNameAndOrderIdResponseVo {

    private Long orderNumber;
    private Long orderId;

    public OrderNameAndOrderIdResponseVo(Long orderNumber, Long orderId) {
        this.orderNumber = orderNumber;
        this.orderId = orderId;
    }

    public static OrderNameAndOrderIdResponseVo convertToVo(OrderNameAndOrderIdResponseDto orderNameAndOrderIdResponseDto) {
        return new OrderNameAndOrderIdResponseVo(
                orderNameAndOrderIdResponseDto.getOrderNumber(),
                orderNameAndOrderIdResponseDto.getOrderId()
        );
    }
}
