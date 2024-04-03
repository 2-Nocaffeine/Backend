package com.nocaffeine.ssgclone.order.dto.request;

import com.nocaffeine.ssgclone.order.vo.request.UserOrderProductRequestVo;
import com.nocaffeine.ssgclone.order.vo.request.OrderedProductRequestVo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class UserOrderSaveRequestDto {

        private List<OrderedProductRequestDto> orderProducts;
        private String uuid;
        private String name;
        private String region;
        private String OrderName;
        private String phoneNumber;
        private String email;
        private int totalPrice;
        private String orderDate;
        private String status;

        public static UserOrderSaveRequestDto convertToDto(String memberUuid, UserOrderProductRequestVo userOrderProductRequestVo) {
            List<OrderedProductRequestDto> orderedProductRequestDtoList = new ArrayList<>();

            for (OrderedProductRequestVo orderProducts : userOrderProductRequestVo.getOrderProducts()){
                orderedProductRequestDtoList.add(OrderedProductRequestDto.convertToDto(orderProducts));
            }

            return UserOrderSaveRequestDto.builder()
                    .orderProducts(orderedProductRequestDtoList)
                    .uuid(memberUuid)
                    .region(userOrderProductRequestVo.getRegion())
                    .name(userOrderProductRequestVo.getName())
                    .OrderName(userOrderProductRequestVo.getOrderName())
                    .phoneNumber(userOrderProductRequestVo.getPhoneNumber())
                    .email(userOrderProductRequestVo.getEmail())
                    .totalPrice(userOrderProductRequestVo.getTotalPrice())
                    .build();
        }
}


