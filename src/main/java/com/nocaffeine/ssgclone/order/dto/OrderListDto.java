package com.nocaffeine.ssgclone.order.dto;

import com.nocaffeine.ssgclone.order.vo.response.OrderListResponseVo;
import com.nocaffeine.ssgclone.order.vo.response.OrderProductListResponseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderListDto {

    private LocalDateTime orderDate;
    private Long orderId;
    private int totalPrice;
    private String name;
    private List<OrderProductListResponseVo> orderProductListResponseVo;

    public static OrderListResponseVo convertToVo(OrderListDto orderListDto){
        return new OrderListResponseVo(orderListDto.orderDate, orderListDto.orderId,
                orderListDto.totalPrice, orderListDto.name, orderListDto.orderProductListResponseVo);
    }
}
