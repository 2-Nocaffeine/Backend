package com.nocaffeine.ssgclone.order.application;

import com.nocaffeine.ssgclone.order.dto.OrderIdDto;
import com.nocaffeine.ssgclone.order.dto.OrderListDto;
import com.nocaffeine.ssgclone.order.dto.UserOrderSaveDto;

import java.util.List;

public interface OrderService {

    void addMemberOrder (UserOrderSaveDto userOrderSaveDto);

    void removeOrder(OrderIdDto orderIdDto);

//    List<OrderListDto> findOrderList(String memberUuid);
}
