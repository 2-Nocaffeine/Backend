package com.nocaffeine.ssgclone.order.application;

import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.order.domain.OrderProduct;
import com.nocaffeine.ssgclone.order.domain.Orders;
import com.nocaffeine.ssgclone.order.dto.OrderIdDto;
import com.nocaffeine.ssgclone.order.dto.OrderListDto;
import com.nocaffeine.ssgclone.order.dto.UserOrderSaveDto;
import com.nocaffeine.ssgclone.order.dto.OrderedProductDto;
import com.nocaffeine.ssgclone.order.infrastructure.OrderProductRepository;
import com.nocaffeine.ssgclone.order.infrastructure.OrderRepository;
import com.nocaffeine.ssgclone.product.domain.OptionSelectedProduct;
import com.nocaffeine.ssgclone.product.infrastructure.OptionSelectedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final OptionSelectedProductRepository optionSelectedProductRepository;

    @Override
    @Transactional
    public void addMemberOrder(UserOrderSaveDto userOrderSaveDto) {

        //재고 확인
        for (OrderedProductDto orderedProductDto : userOrderSaveDto.getOrderProducts()) {
            OptionSelectedProduct optionSelectedProduct = optionSelectedProductRepository.findById(orderedProductDto.getOptionSelectedProductId())
                    .orElseThrow(() -> new BaseException(NO_PRODUCT));

            optionSelectedProduct.decreaseStock(orderedProductDto.getCount());
        }
        //주문 저장
        Orders Order = Orders.builder()
                .uuid(userOrderSaveDto.getUuid())
                .region(userOrderSaveDto.getRegion())
                .name(userOrderSaveDto.getName())
                .phoneNumber(userOrderSaveDto.getPhoneNumber())
                .email(userOrderSaveDto.getEmail())
                .totalPrice(userOrderSaveDto.getTotalPrice())
                .orderDate(Timestamp.valueOf(LocalDateTime.now()))
                .status(Orders.OrderStatus.ORDERED)
                .build();

        Orders savedOrders = orderRepository.save(Order);

        //주문상품 저장
        for (OrderedProductDto orderedProductDto : userOrderSaveDto.getOrderProducts()) {
            OptionSelectedProduct optionSelectedProduct = optionSelectedProductRepository.findById(orderedProductDto.getOptionSelectedProductId())
                    .orElseThrow(() -> new BaseException(NO_PRODUCT));

            OrderProduct orderProduct = OrderProduct.builder()
                    .order(savedOrders)
                    .optionSelectedProduct(optionSelectedProduct)
                    .price(orderedProductDto.getPrice())
                    .quantity(orderedProductDto.getCount())
                    .build();

            orderProductRepository.save(orderProduct);
        }

    }

    @Override
    @Transactional
    public void removeOrder(OrderIdDto orderIdDto) {
        Orders order = orderRepository.findById(orderIdDto.getOrderId())
                .orElseThrow(() -> new BaseException(NO_EXIST_ORDER));

        order.changeStatus(Orders.OrderStatus.CANCEL);

    }

//    @Override
//    public List<OrderListDto> findOrderList(String memberUuid) {
////        Orders order = orderRepository.findByUuid(memberUuid)
////                .orElseThrow(() -> new BaseException(NO_ORDER_HISTORY));
//
//
//
//    }


}


