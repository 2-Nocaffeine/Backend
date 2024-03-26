package com.nocaffeine.ssgclone.order.application;

import com.nocaffeine.ssgclone.order.dto.MemberOrderSaveDto;

public interface OrderService {

    void addMemberOrder (MemberOrderSaveDto memberOrderSaveDto);
}
