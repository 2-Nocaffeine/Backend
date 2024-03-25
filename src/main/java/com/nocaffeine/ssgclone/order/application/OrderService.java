package com.nocaffeine.ssgclone.order.application;

import com.nocaffeine.ssgclone.order.dto.request.OrderSaveRequest;
import com.nocaffeine.ssgclone.order.dto.response.OrderSaveResponse;

public interface OrderService {

    String saveOrder(OrderSaveRequest orderRequest);
}
