package com.nocaffeine.ssgclone.deliveryaddress.dto.response;

import com.nocaffeine.ssgclone.deliveryaddress.vo.response.DeliveryAddressDefaultResponseVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DeliveryAddressDefaultResponseDto {
    private Long deliveryAddressId;
    private String addressName;
    private String recipient;
    private String phoneNumber;
    private String zip;
    private String post;
    private String street;
    private boolean defaultCheck;

    public static DeliveryAddressDefaultResponseVo dtoToVo(DeliveryAddressDefaultResponseDto defaultDeliveryAddress) {
        return new DeliveryAddressDefaultResponseVo(
            defaultDeliveryAddress.getDeliveryAddressId(),
            defaultDeliveryAddress.getAddressName(),
            defaultDeliveryAddress.getRecipient(),
            defaultDeliveryAddress.getPhoneNumber(),
            defaultDeliveryAddress.getZip(),
            defaultDeliveryAddress.getPost(),
            defaultDeliveryAddress.getStreet(),
            defaultDeliveryAddress.isDefaultCheck());


    }
}
