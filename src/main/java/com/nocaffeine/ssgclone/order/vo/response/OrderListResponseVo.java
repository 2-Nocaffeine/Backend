package com.nocaffeine.ssgclone.order.vo.response;

import com.nocaffeine.ssgclone.order.vo.request.OrderedProductRequestVo;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderListResponseVo {

    private LocalDateTime orderDate;
    private Long orderId;
    private int totalPrice;
    private String name;
    private List<OrderProductListResponseVo> orderProductListResponseVo;

    public OrderListResponseVo(LocalDateTime orderDate, Long orderId, int totalPrice, String name,
                               List<OrderProductListResponseVo> orderProductListResponseVo)
    {
        this.orderDate = orderDate;
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.name = name;
        this.orderProductListResponseVo = orderProductListResponseVo;
    }
}
