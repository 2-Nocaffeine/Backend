package com.nocaffeine.ssgclone.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberOrderInfoResponseDto {

    private String orderName;
    private String phoneNumber;
    private String email;

}
