package com.nocaffeine.ssgclone.order.application;

import com.nocaffeine.ssgclone.order.domain.Orders;
import com.nocaffeine.ssgclone.order.infrastructure.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    
}
