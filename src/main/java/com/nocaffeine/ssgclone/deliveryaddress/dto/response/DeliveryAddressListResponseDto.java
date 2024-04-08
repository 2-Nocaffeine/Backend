package com.nocaffeine.ssgclone.deliveryaddress.dto.response;

import com.nocaffeine.ssgclone.deliveryaddress.vo.response.DeliveryAddressListResponseVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DeliveryAddressListResponseDto {
    private Long deliveryAddressId;
    private String addressName;
    private String recipient;
    private String phoneNumber;
    private String zip;
    private String post;
    private String street;
    private boolean defaultCheck;

    public static DeliveryAddressListResponseVo dtoToVo(DeliveryAddressListResponseDto address) {
        DeliveryAddressListResponseVo vo = new DeliveryAddressListResponseVo(
                address.getDeliveryAddressId(),
                address.getAddressName(),
                address.getRecipient(),
                address.getPhoneNumber(),
                address.getZip(),
                address.getPost(),
                address.getStreet(),
                address.isDefaultCheck()
        );
        return vo;
    }
}
