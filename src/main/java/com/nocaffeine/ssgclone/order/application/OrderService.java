package com.nocaffeine.ssgclone.order.application;

import com.nocaffeine.ssgclone.order.dto.UserOrderSaveDto;

public interface OrderService {

    void addMemberOrder (UserOrderSaveDto userOrderSaveDto);
}
