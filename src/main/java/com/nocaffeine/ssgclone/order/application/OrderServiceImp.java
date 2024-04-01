package com.nocaffeine.ssgclone.order.application;

import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import com.nocaffeine.ssgclone.order.domain.OrderProduct;
import com.nocaffeine.ssgclone.order.domain.Orders;
import com.nocaffeine.ssgclone.order.dto.MemberOrderInfoDto;
import com.nocaffeine.ssgclone.order.dto.OrderIdDto;
import com.nocaffeine.ssgclone.order.dto.UserOrderSaveDto;
import com.nocaffeine.ssgclone.order.dto.OrderedProductDto;
import com.nocaffeine.ssgclone.order.infrastructure.OrderProductRepository;
import com.nocaffeine.ssgclone.order.infrastructure.OrderRepository;
import com.nocaffeine.ssgclone.product.domain.OptionSelectedProduct;
import com.nocaffeine.ssgclone.product.infrastructure.OptionSelectedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final OptionSelectedProductRepository optionSelectedProductRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void addMemberOrder(UserOrderSaveDto userOrderSaveDto) {

        //주문 저장
        Orders order = Orders.toEntity(userOrderSaveDto);
        Orders savedOrders = orderRepository.save(order);

        //재고 확인 후 주문상품 저장
        for (OrderedProductDto orderedProductDto : userOrderSaveDto.getOrderProducts()) {
            OptionSelectedProduct optionSelectedProduct = optionSelectedProductRepository.findById(orderedProductDto.getOptionSelectedProductId())
                    .orElseThrow(() -> new BaseException(NO_PRODUCT));

            OptionSelectedProduct updateOptionSelectedProduct = OptionSelectedProduct.builder()
                    .id(optionSelectedProduct.getId())
                    .product(optionSelectedProduct.getProduct())
                    .sizeOption(optionSelectedProduct.getSizeOption())
                    .colorOption(optionSelectedProduct.getColorOption())
                    .addOption(optionSelectedProduct.getAddOption())
                    .stock(optionSelectedProduct.getStock() - orderedProductDto.getCount())
                    .build();
            optionSelectedProductRepository.save(updateOptionSelectedProduct);

//            optionSelectedProduct.decreaseStock(orderedProductDto.getCount());

            OrderProduct orderProduct = OrderProduct.toEntity(savedOrders, optionSelectedProduct, orderedProductDto);
            orderProductRepository.save(orderProduct);

        }

    }

    @Override
    @Transactional
    public void removeOrder(OrderIdDto orderIdDto) {
        Orders order = orderRepository.findById(orderIdDto.getOrderId())
                .orElseThrow(() -> new BaseException(NO_EXIST_ORDER));

        //주문 취소 이력 확인
        if (order.getStatus() == Orders.OrderStatus.CANCEL){
            throw new BaseException(ALREADY_CANCEL_ORDER);
        }

        Orders updatedOrder = Orders.builder()
                .id(order.getId())
                .uuid(order.getUuid())
                .region(order.getRegion())
                .name(order.getName())
                .phoneNumber(order.getPhoneNumber())
                .email(order.getEmail())
                .totalPrice(order.getTotalPrice())
                .status(Orders.OrderStatus.CANCEL)
                .build();
        orderRepository.save(updatedOrder); //save함으로써 update create_at을 null로 보냄 (updatable = false) 이거있으면 괜찮음

//        order.changeStatus(Orders.OrderStatus.CANCEL); //더티체킹..

        //재고 복구
        //주문한 상품 id 리스트
        List<OrderProduct> orderProductIdList = orderProductRepository.findByOrder(order);

        for (OrderProduct orderProduct : orderProductIdList){
            OptionSelectedProduct optionSelectedProduct = optionSelectedProductRepository.findById(orderProduct.getOptionSelectedProduct().getId())
                    .orElseThrow(() -> new BaseException(NO_PRODUCT));

            OptionSelectedProduct updateOptionSelectedProduct = OptionSelectedProduct.builder()
                    .id(optionSelectedProduct.getId())
                    .product(optionSelectedProduct.getProduct())
                    .sizeOption(optionSelectedProduct.getSizeOption())
                    .colorOption(optionSelectedProduct.getColorOption())
                    .addOption(optionSelectedProduct.getAddOption())
                    .stock(optionSelectedProduct.getStock() + orderProduct.getQuantity())
                    .build();

            optionSelectedProductRepository.save(updateOptionSelectedProduct);


        }
    }

    @Override
    public MemberOrderInfoDto findOrderInfo(String memberUuid) {
        Member member =  memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        return MemberOrderInfoDto.builder()
                .orderName(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .email(member.getEmail())
                .build();
    }


}


