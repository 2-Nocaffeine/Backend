package com.nocaffeine.ssgclone.order.application;

import com.nocaffeine.ssgclone.order.dto.request.GuestOrderInfoRequestDto;
import com.nocaffeine.ssgclone.order.dto.request.OrderNumberRequestDto;
import com.nocaffeine.ssgclone.order.dto.response.MemberOrderInfoResponseDto;
import com.nocaffeine.ssgclone.order.dto.request.OrderIdRequestDto;
import com.nocaffeine.ssgclone.order.dto.request.UserOrderSaveRequestDto;
import com.nocaffeine.ssgclone.order.dto.response.OrderIdListResponseDto;
import com.nocaffeine.ssgclone.order.dto.response.OrderInfoAndProductListResponseDto;

import java.util.List;

public interface OrderService {

    void addMemberOrder (UserOrderSaveRequestDto userOrderSaveRequestDto);

    void removeOrder(OrderIdRequestDto orderIdRequestDto);

    MemberOrderInfoResponseDto findOrderInfo(String memberUuid);

    List<OrderIdListResponseDto> findOrderIdList(String memberUuid);

    OrderInfoAndProductListResponseDto findOrderProductList(OrderNumberRequestDto orderNumberRequestDto);


    OrderInfoAndProductListResponseDto findGuestOrderInfo(GuestOrderInfoRequestDto guestOrderInfoRequestDto);
}
