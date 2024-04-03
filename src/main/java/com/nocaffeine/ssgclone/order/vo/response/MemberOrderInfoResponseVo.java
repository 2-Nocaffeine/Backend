package com.nocaffeine.ssgclone.order.vo.response;

import com.nocaffeine.ssgclone.order.dto.response.MemberOrderInfoResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberOrderInfoResponseVo {

    private String orderName;
    private String phoneNumber;
    private String email;

    private MemberOrderInfoResponseVo(String orderName, String phoneNumber, String email){
        this.orderName = orderName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public static MemberOrderInfoResponseVo convertToVo(MemberOrderInfoResponseDto memberOrderInfoResponseDto) {
        return new MemberOrderInfoResponseVo(
                memberOrderInfoResponseDto.getOrderName(),
                memberOrderInfoResponseDto.getPhoneNumber(),
                memberOrderInfoResponseDto.getEmail()
        );
    }
}
