package com.nocaffeine.ssgclone.order.dto.response;

import com.nocaffeine.ssgclone.order.domain.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderIdListResponseDto {

    private Long orderId;

}
