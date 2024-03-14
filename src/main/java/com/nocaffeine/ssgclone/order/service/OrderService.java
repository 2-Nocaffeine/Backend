package com.nocaffeine.ssgclone.order.service;

import com.nocaffeine.ssgclone.order.domain.Orders;
import com.nocaffeine.ssgclone.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    //    토큰 추출
    String token = "your-jwt-token";
    String uuid = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();

//    디비 검색
    @Autowired
    private OrderRepository orderRepository;

    Orders order = new Orders();
    order.setMemberId(member.getUUID());
    orderRepository.save(order)

}
