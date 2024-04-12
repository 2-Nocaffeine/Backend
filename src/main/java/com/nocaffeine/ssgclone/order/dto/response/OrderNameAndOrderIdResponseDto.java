package com.nocaffeine.ssgclone.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderNameAndOrderIdResponseDto {

    private Long orderNumber;
    private Long orderId;
}
