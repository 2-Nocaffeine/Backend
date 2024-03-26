package com.nocaffeine.ssgclone.order.application;

import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.order.domain.OrderProduct;
import com.nocaffeine.ssgclone.order.domain.Orders;
import com.nocaffeine.ssgclone.order.dto.MemberOrderProductIdDto;
import com.nocaffeine.ssgclone.order.dto.MemberOrderSaveDto;
import com.nocaffeine.ssgclone.order.infrastructure.OrderProductRepository;
import com.nocaffeine.ssgclone.order.infrastructure.OrderRepository;
import com.nocaffeine.ssgclone.product.domain.OptionSelectedProduct;
import com.nocaffeine.ssgclone.product.infrastructure.OptionSelectedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_DATA;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final OptionSelectedProductRepository optionSelectedProductRepository;

    @Override
    @Transactional
    public void addMemberOrder(MemberOrderSaveDto memberOrderSaveDto) {

        Orders Order = Orders.builder()
                .uuid(memberOrderSaveDto.getUuid())
                .region(memberOrderSaveDto.getRegion())
                .name(memberOrderSaveDto.getName())
                .phoneNumber(memberOrderSaveDto.getPhoneNumber())
                .email(memberOrderSaveDto.getEmail())
                .totalPrice(memberOrderSaveDto.getTotalPrice())
                .orderDate(Timestamp.valueOf(LocalDateTime.now()))
                .status(Orders.OrderStatus.ORDERED)
                .build();

        Orders savedOrders = orderRepository.save(Order);


//        string list로 아이디값을 받고 그 아이디값으로 orderprodct를 생성해야함
        for (Long productId : memberOrderSaveDto.getOptionSelectedProducts()) {
            OptionSelectedProduct optionSelectedProductId = optionSelectedProductRepository.findById(productId)
                    .orElseThrow(() -> new BaseException(NO_DATA));

            OrderProduct orderProduct = OrderProduct.builder()
                    .order(savedOrders)
                    .optionSelectedProduct(optionSelectedProductId)
                    .build();

            orderProductRepository.save(orderProduct);
        }




    }



}


