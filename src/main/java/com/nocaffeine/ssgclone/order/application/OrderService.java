package com.nocaffeine.ssgclone.order.application;

import com.nocaffeine.ssgclone.order.dto.request.OrderNumberRequestDto;
import com.nocaffeine.ssgclone.order.dto.response.MemberOrderInfoResponseDto;
import com.nocaffeine.ssgclone.order.dto.request.OrderIdRequestDto;
import com.nocaffeine.ssgclone.order.dto.request.UserOrderSaveRequestDto;
import com.nocaffeine.ssgclone.order.dto.response.OrderIdListResponseDto;
import com.nocaffeine.ssgclone.order.dto.response.OrderInfoAndProductListResponseDto;

public interface OrderService {

    void addMemberOrder (UserOrderSaveRequestDto userOrderSaveRequestDto);

    void removeOrder(OrderIdRequestDto orderIdRequestDto);

    MemberOrderInfoResponseDto findOrderInfo(String memberUuid);

    OrderIdListResponseDto findOrderIdList(String memberUuid);

    OrderInfoAndProductListResponseDto findOrderProductList(OrderNumberRequestDto orderNumberRequestDto);
}
