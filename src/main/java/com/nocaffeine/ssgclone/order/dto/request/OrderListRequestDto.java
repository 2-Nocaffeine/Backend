package com.nocaffeine.ssgclone.order.dto.request;

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
public class OrderListRequestDto {

    private LocalDateTime orderDate;
    private Long orderId;
    private int totalPrice;
    private String Name;
    private List<OrderProductListResponseVo> orderProductListResponseVo;

    public static OrderListResponseVo convertToVo(OrderListRequestDto orderListRequestDto){
        return new OrderListResponseVo(orderListRequestDto.orderDate, orderListRequestDto.orderId,
                orderListRequestDto.totalPrice, orderListRequestDto.Name, orderListRequestDto.orderProductListResponseVo);
    }
}
