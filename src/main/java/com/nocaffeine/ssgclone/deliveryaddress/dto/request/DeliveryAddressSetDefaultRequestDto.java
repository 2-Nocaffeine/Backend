package com.nocaffeine.ssgclone.deliveryaddress.dto.request;

import com.nocaffeine.ssgclone.deliveryaddress.vo.request.DeliveryAddressSetDefaultRequestVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DeliveryAddressSetDefaultRequestDto {
    private Long deliveryAddressId;

    public static DeliveryAddressSetDefaultRequestDto voToDto(DeliveryAddressSetDefaultRequestVo deliveryAddressAddRequestVo) {
        return DeliveryAddressSetDefaultRequestDto.builder()
                .deliveryAddressId(deliveryAddressAddRequestVo.getDeliveryAddressId())
                .build();
    }
}
