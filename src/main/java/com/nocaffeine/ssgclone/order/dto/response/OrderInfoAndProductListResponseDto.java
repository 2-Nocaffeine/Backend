package com.nocaffeine.ssgclone.order.dto.response;

import com.nocaffeine.ssgclone.order.domain.Orders;
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
public class OrderInfoAndProductListResponseDto {

    private Long orderNumber;
    private Long orderId;
    private String receiverName;
    private int totalPrice;
    private Orders.OrderStatus orderStatus;
    private LocalDateTime orderDate;

    private List<OrderProductListResponseDto> orderProductList;
}
