package com.nocaffeine.ssgclone.order.dto.request;

import com.nocaffeine.ssgclone.order.vo.request.GuestOrderInfoRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestOrderInfoRequestDto {

    private String orderName;
    private String orderPhone;
    private Long orderNumber;

//    public static GuestOrderInfoRequestDto convertToDto(GuestOrderInfoRequestVo guestOrderInfoRequestVo) {
//        return GuestOrderInfoRequestDto.builder()
//                .orderName(guestOrderInfoRequestVo.getOrderName())
//                .orderPhone(guestOrderInfoRequestVo.getOrderPhone())
//                .orderNumber(guestOrderInfoRequestVo.getOrderNumber())
//                .build();
//    }
}
