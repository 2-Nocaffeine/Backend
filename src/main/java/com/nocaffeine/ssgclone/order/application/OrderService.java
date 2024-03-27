package com.nocaffeine.ssgclone.order.application;

import com.nocaffeine.ssgclone.order.dto.MemberOrderSaveDto;

import java.util.List;

public interface OrderService {

    void addMemberOrder (MemberOrderSaveDto memberOrderSaveDto);
}
