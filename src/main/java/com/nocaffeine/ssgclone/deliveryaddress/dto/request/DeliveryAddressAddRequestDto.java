package com.nocaffeine.ssgclone.deliveryaddress.dto.request;

import com.nocaffeine.ssgclone.deliveryaddress.vo.request.DeliveryAddressAddRequestVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DeliveryAddressAddRequestDto {
    private String addressName;
    private String recipient;
    private String phoneNumber;
    private String zip;
    private String post;
    private String street;

    public static DeliveryAddressAddRequestDto voToDto(DeliveryAddressAddRequestVo deliveryAddressAddRequestVo) {
        return DeliveryAddressAddRequestDto.builder()
                .addressName(deliveryAddressAddRequestVo.getAddressName())
                .recipient(deliveryAddressAddRequestVo.getRecipient())
                .phoneNumber(deliveryAddressAddRequestVo.getPhoneNumber())
                .zip(deliveryAddressAddRequestVo.getZip())
                .post(deliveryAddressAddRequestVo.getPost())
                .street(deliveryAddressAddRequestVo.getStreet())
                .build();
    }
}
