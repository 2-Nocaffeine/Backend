package com.nocaffeine.ssgclone.order.application;

import com.nocaffeine.ssgclone.brandstore.infrastructure.BrandListRepository;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import com.nocaffeine.ssgclone.order.domain.OrderProduct;
import com.nocaffeine.ssgclone.order.domain.Orders;
import com.nocaffeine.ssgclone.order.dto.request.*;
import com.nocaffeine.ssgclone.order.dto.response.MemberOrderInfoResponseDto;
import com.nocaffeine.ssgclone.order.dto.response.OrderIdListResponseDto;
import com.nocaffeine.ssgclone.order.dto.response.OrderInfoAndProductListResponseDto;
import com.nocaffeine.ssgclone.order.dto.response.OrderProductListResponseDto;
import com.nocaffeine.ssgclone.order.infrastructure.OrderProductRepository;
import com.nocaffeine.ssgclone.order.infrastructure.OrderRepository;
import com.nocaffeine.ssgclone.product.domain.OptionSelectedProduct;
import com.nocaffeine.ssgclone.product.infrastructure.ImageRepository;
import com.nocaffeine.ssgclone.product.infrastructure.OptionSelectedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

            String thumbnailUrl = imageRepository.findByUrlQuery(orderedProductRequestDto.getThumbnailId());
            String brandName = brandListRepository.findBrandNameByProductId(orderedProductRequestDto.getOptionSelectedProductId());

            OrderProduct orderProduct = OrderProduct.builder()
                    .order(savedOrders)
                    .productId(optionSelectedProduct.getProduct().getId())
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
        List<OrderProduct> orderProductIdList = orderProductRepository.findAllByOrder(order);

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
    public List<OrderIdListResponseDto> findOrderIdList(String memberUuid) {

        List<OrderIdListResponseDto> orderIdDtoList = new ArrayList<>();

        for (Orders order : orderRepository.findByUuid(memberUuid)){
            OrderIdListResponseDto orderIdDto = OrderIdListResponseDto.builder()
                    .orderId(order.getId())
                    .build();
            orderIdDtoList.add(orderIdDto);
        }
        return orderIdDtoList;

    }

    @Override
    public OrderInfoAndProductListResponseDto findOrderProductList(OrderNumberRequestDto orderNumberRequestDto) {

        Orders order = orderRepository.findById(orderNumberRequestDto.getOrderId())
                .orElseThrow(() -> new BaseException(NO_EXIST_ORDER));

        List<OrderProduct> orderProductList = orderProductRepository.findAllByOrder(order);

        List<OrderProductListResponseDto> orderProductinfoList = new ArrayList<>();

        for (OrderProduct orderProduct : orderProductList){

            OrderProductListResponseDto orderProductOne = OrderProductListResponseDto.builder()
                    .productName(orderProduct.getProductName())
                    .productId(orderProduct.getProductId())
                    .addOption(orderProduct.getAddOption())
                    .color(orderProduct.getColor())
                    .size(orderProduct.getSize())
                    .count(orderProduct.getQuantity())
                    .price(orderProduct.getPrice())
                    .brand(orderProduct.getBrand())
                    .thumbnail(orderProduct.getThumbnailUrl())
                    .build();

            orderProductinfoList.add(orderProductOne);
        }

        return OrderInfoAndProductListResponseDto.builder()
                .orderNumber(order.getOrderNumber())
                .orderId(order.getId())
                .receiverName(order.getName())
                .totalPrice(order.getTotalPrice())
                .orderStatus(order.getStatus())
                .orderDate(order.getCreatedAt())
                .orderProductList(orderProductinfoList)
                .build();

    }

//    @Override
//    public OrderInfoAndProductListResponseDto findGuestOrderInfo(GuestOrderInfoRequestDto guestOrderInfoRequestDto) {
//
//        Orders order = orderRepository.findByOrderPhoneAndOrderNumber(guestOrderInfoRequestDto.getOrderPhone(),guestOrderInfoRequestDto.getOrderNumber())
//                .orElseThrow(() -> new BaseException(NO_EXIST_ORDER));
//
//        List<OrderProduct> orderProductList = orderProductRepository.findAllByOrder(order);
//
//        for (OrderProduct orderProduct : orderProductList){
//
//        }
//    }


}


