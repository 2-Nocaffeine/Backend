package com.nocaffeine.ssgclone.order.application;

import com.nocaffeine.ssgclone.order.domain.Orders;
import com.nocaffeine.ssgclone.order.dto.request.OrderSaveRequest;
import com.nocaffeine.ssgclone.order.dto.response.OrderSaveResponse;
import com.nocaffeine.ssgclone.order.infrastructure.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public String saveOrder(OrderSaveRequest orderRequest) {

        Orders orders = Orders.builder()
                .id(orderRequest.getId())
                .uuid()

        return ;
    }
}


