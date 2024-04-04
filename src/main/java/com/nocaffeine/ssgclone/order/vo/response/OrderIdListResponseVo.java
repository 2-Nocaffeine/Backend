package com.nocaffeine.ssgclone.order.vo.response;

import com.nocaffeine.ssgclone.order.dto.response.OrderIdListResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderIdListResponseVo {

    private Long orderId;

    public OrderIdListResponseVo(Long orderId) {
        this.orderId = orderId;
    }

    public static List<OrderIdListResponseVo> convertToVo(List<OrderIdListResponseDto> orderIdList) {

        List<OrderIdListResponseVo> orderIdListResponseVos = new ArrayList<>();

        for (OrderIdListResponseDto orderIdListResponseDto : orderIdList) {
            orderIdListResponseVos.add(new OrderIdListResponseVo(orderIdListResponseDto.getOrderId()));
        }
            return orderIdListResponseVos;
    }
}
