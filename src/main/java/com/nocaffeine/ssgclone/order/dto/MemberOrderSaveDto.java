package com.nocaffeine.ssgclone.order.dto;

import com.nocaffeine.ssgclone.order.vo.request.MemberOrderProductRequestVo;
import com.nocaffeine.ssgclone.order.vo.request.OrderedProductVo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class MemberOrderSaveDto {

        private List<OrderedProductDto> orderProducts;
        private String uuid;
        private String region;
        private String name;
        private String phoneNumber;
        private String email;
        private int totalPrice;
        private String orderDate;
        private String status;

        public static MemberOrderSaveDto convertToDto(String memberUuid,MemberOrderProductRequestVo memberOrderProductRequestVo) {
            List<OrderedProductDto> OrderedProductDtoList = new ArrayList<>();

            for (OrderedProductVo orderProducts : memberOrderProductRequestVo.getOrderProducts()){
                OrderedProductDtoList.add(OrderedProductDto.convertToDto(orderProducts));
            }

            return MemberOrderSaveDto.builder()
                    .orderProducts(OrderedProductDtoList)
                    .uuid(memberUuid)
                    .region(memberOrderProductRequestVo.getRegion())
                    .name(memberOrderProductRequestVo.getName())
                    .phoneNumber(memberOrderProductRequestVo.getPhoneNumber())
                    .email(memberOrderProductRequestVo.getEmail())
                    .totalPrice(memberOrderProductRequestVo.getTotalPrice())
                    .build();
        }
}


