package com.nocaffeine.ssgclone.order.vo.response;

import com.nocaffeine.ssgclone.order.dto.response.OrderIdListResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderIdListResponseVo {

    private List<Long> orderIdList;


    private OrderIdListResponseVo(List<Long> orderIdList){
        this.orderIdList = orderIdList;
    }
    public static OrderIdListResponseVo convertToVo(OrderIdListResponseDto orderIdListResponseDto) {

        return new OrderIdListResponseVo(
                orderIdListResponseDto.getOrderIdList()
        );


    }
}
