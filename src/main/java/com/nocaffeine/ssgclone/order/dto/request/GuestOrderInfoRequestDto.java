package com.nocaffeine.ssgclone.order.dto.request;

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


}
