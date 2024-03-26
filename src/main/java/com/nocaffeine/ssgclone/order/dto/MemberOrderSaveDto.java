package com.nocaffeine.ssgclone.order.dto;

import com.nocaffeine.ssgclone.order.domain.Orders;
import com.nocaffeine.ssgclone.order.vo.request.MemberOrderSaveRequestVo;
import com.nocaffeine.ssgclone.product.domain.OptionSelectedProduct;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class MemberOrderSaveDto {

        private List<Long> optionSelectedProducts;
        private String uuid;
        private String region;
        private String name;
        private String phoneNumber;
        private String email;
        private int totalPrice;
        private String orderDate;
        private String status;

        public static MemberOrderSaveDto convertToDto(String memberUuid,MemberOrderSaveRequestVo vo) {

                MemberOrderSaveDto dto = MemberOrderSaveDto.builder()

                        .optionSelectedProducts(vo.getOptionSelectedProducts())
                        .uuid(memberUuid)
                        .region(vo.getRegion())
                        .name(vo.getName())
                        .phoneNumber(vo.getPhoneNumber())
                        .email(vo.getEmail())
                        .totalPrice(vo.getTotalPrice())
                        .build();
                return dto;
        }
}


