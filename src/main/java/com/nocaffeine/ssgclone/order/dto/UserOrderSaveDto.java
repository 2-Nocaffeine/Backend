package com.nocaffeine.ssgclone.order.dto;

import com.nocaffeine.ssgclone.order.vo.request.UserOrderProductRequestVo;
import com.nocaffeine.ssgclone.order.vo.request.OrderedProductVo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class UserOrderSaveDto {

        private List<OrderedProductDto> orderProducts;
        private String uuid;
        private String region;
        private String name;
        private String phoneNumber;
        private String email;
        private int totalPrice;
        private String orderDate;
        private String status;

        public static UserOrderSaveDto convertToDto(String memberUuid, UserOrderProductRequestVo userOrderProductRequestVo) {
            List<OrderedProductDto> OrderedProductDtoList = new ArrayList<>();

            for (OrderedProductVo orderProducts : userOrderProductRequestVo.getOrderProducts()){
                OrderedProductDtoList.add(OrderedProductDto.convertToDto(orderProducts));
            }

            return UserOrderSaveDto.builder()
                    .orderProducts(OrderedProductDtoList)
                    .uuid(memberUuid)
                    .region(userOrderProductRequestVo.getRegion())
                    .name(userOrderProductRequestVo.getName())
                    .phoneNumber(userOrderProductRequestVo.getPhoneNumber())
                    .email(userOrderProductRequestVo.getEmail())
                    .totalPrice(userOrderProductRequestVo.getTotalPrice())
                    .build();
        }
}


