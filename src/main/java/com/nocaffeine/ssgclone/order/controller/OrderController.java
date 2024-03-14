package com.nocaffeine.ssgclone.order.controller;

import com.nocaffeine.ssgclone.order.dto.OrderDto;
import com.nocaffeine.ssgclone.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/api/v1/order")
    public String order(@ModelAttribute OrderDto orderDto) {
        orderService.order();
    }
}
