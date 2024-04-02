package com.nocaffeine.ssgclone.order.application;

import com.nocaffeine.ssgclone.brandstore.infrastructure.BrandListRepository;
import com.nocaffeine.ssgclone.brandstore.infrastructure.BrandRepository;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import com.nocaffeine.ssgclone.order.domain.OrderProduct;
import com.nocaffeine.ssgclone.order.domain.Orders;
import com.nocaffeine.ssgclone.order.dto.response.MemberOrderInfoResponseDto;
import com.nocaffeine.ssgclone.order.dto.request.OrderIdRequestDto;
import com.nocaffeine.ssgclone.order.dto.request.UserOrderSaveRequestDto;
import com.nocaffeine.ssgclone.order.dto.request.OrderedProductRequestDto;
import com.nocaffeine.ssgclone.order.dto.response.OrderIdListResponseDto;
import com.nocaffeine.ssgclone.order.infrastructure.OrderProductRepository;
import com.nocaffeine.ssgclone.order.infrastructure.OrderRepository;
import com.nocaffeine.ssgclone.product.domain.OptionSelectedProduct;
import com.nocaffeine.ssgclone.product.infrastructure.ImageRepository;
import com.nocaffeine.ssgclone.product.infrastructure.OptionSelectedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final OptionSelectedProductRepository optionSelectedProductRepository;
    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;
    private final BrandListRepository brandListRepository;

    @Override
    @Transactional
    public void addMemberOrder(UserOrderSaveRequestDto userOrderSaveRequestDto) {

        //주문 저장
        Orders order = Orders.toEntity(userOrderSaveRequestDto);
        Orders savedOrders = orderRepository.save(order);

        //재고 확인 후 주문상품 저장
        for (OrderedProductRequestDto orderedProductRequestDto : userOrderSaveRequestDto.getOrderProducts()) {
            OptionSelectedProduct optionSelectedProduct = optionSelectedProductRepository.findById(orderedProductRequestDto.getOptionSelectedProductId())
                    .orElseThrow(() -> new BaseException(NO_PRODUCT));

            OptionSelectedProduct updateOptionSelectedProduct = OptionSelectedProduct
                    .decreaseStock(optionSelectedProduct, orderedProductRequestDto.getCount());

            optionSelectedProductRepository.save(updateOptionSelectedProduct);

            String thumbnailUrl = imageRepository.findByUrl(orderedProductRequestDto.getThumbnailId());
            String brandName = brandListRepository.findBrandNameByProductId(orderedProductRequestDto.getOptionSelectedProductId());

            OrderProduct orderProduct = OrderProduct.builder()
                    .order(savedOrders)
                    .productName(optionSelectedProduct.getProduct().getName())
                    .price(orderedProductRequestDto.getPrice())
                    .quantity(orderedProductRequestDto.getCount())
                    .thumbnailUrl(thumbnailUrl)
                    .color(optionSelectedProduct.getColorOption().getColor())
                    .size(optionSelectedProduct.getSizeOption().getSize())
                    .addOption(optionSelectedProduct.getAddOption().getOptionName())
                    .brand(brandName)
                    .build();

            orderProductRepository.save(orderProduct);

        }

    }

    @Override
    @Transactional
    public void removeOrder(OrderIdRequestDto orderIdRequestDto) {
        Orders order = orderRepository.findByOrderNumber(orderIdRequestDto.getOrderNumber())
                .orElseThrow(() -> new BaseException(NO_EXIST_ORDER));

        //주문 취소 이력 확인
        if (order.getStatus() == Orders.OrderStatus.CANCEL){
            throw new BaseException(ALREADY_CANCEL_ORDER);
        }

        Orders updatedOrder = Orders.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .uuid(order.getUuid())
                .region(order.getRegion())
                .name(order.getName())
                .phoneNumber(order.getPhoneNumber())
                .email(order.getEmail())
                .totalPrice(order.getTotalPrice())
                .status(Orders.OrderStatus.CANCEL)
                .build();

        orderRepository.save(updatedOrder);

//        order.changeStatus(Orders.OrderStatus.CANCEL); //더티체킹..

        //재고 복구
        //주문한 상품 객체 리스트
        List<OrderProduct> orderProductIdList = orderProductRepository.findByOrder(order);

        for (OrderProduct orderProduct : orderProductIdList){
            OptionSelectedProduct optionSelectedProduct = optionSelectedProductRepository.findById(orderProduct.getId())
                    .orElseThrow(() -> new BaseException(NO_PRODUCT));

            OptionSelectedProduct updateOptionSelectedProduct = OptionSelectedProduct
                    .increaseStock(optionSelectedProduct, orderProduct.getQuantity());

            optionSelectedProductRepository.save(updateOptionSelectedProduct);


        }
    }

    @Override
    public MemberOrderInfoResponseDto findOrderInfo(String memberUuid) {
        Member member =  memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        return MemberOrderInfoResponseDto.builder()
                .orderName(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .email(member.getEmail())
                .build();
    }

    @Override
    public OrderIdListResponseDto findOrderIdList(String memberUuid) {

        List<Long> orderIdList = orderRepository.findByUuid(memberUuid);

        return OrderIdListResponseDto.builder()
                .orderIdList(orderIdList)
                .build();

    }


}


