package com.nocaffeine.ssgclone.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberOrderInfoDto {

    private String orderName;
    private String phoneNumber;
    private String email;
}
