package com.nocaffeine.ssgclone.deliveryaddress.dto.request;

import com.nocaffeine.ssgclone.deliveryaddress.vo.request.DeliveryAddressModifyRequestVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DeliveryAddressModifyRequestDto {
    private Long deliveryAddressId;
    private String addressName;
    private String recipient;
    private String phoneNumber;
    private String zip;
    private String post;
    private String street;

    public static DeliveryAddressModifyRequestDto voToDto(DeliveryAddressModifyRequestVo deliveryAddressAddRequestVo) {
        return DeliveryAddressModifyRequestDto.builder()
                .deliveryAddressId(deliveryAddressAddRequestVo.getDeliveryAddressId())
                .addressName(deliveryAddressAddRequestVo.getAddressName())
                .recipient(deliveryAddressAddRequestVo.getRecipient())
                .phoneNumber(deliveryAddressAddRequestVo.getPhoneNumber())
                .zip(deliveryAddressAddRequestVo.getZip())
                .post(deliveryAddressAddRequestVo.getPost())
                .street(deliveryAddressAddRequestVo.getStreet())
                .build();
    }
}
