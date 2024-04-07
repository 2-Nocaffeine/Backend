package com.nocaffeine.ssgclone.deliveryaddress.dto.response;


import com.nocaffeine.ssgclone.deliveryaddress.vo.response.DeliveryAddressDetailResponseVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DeliveryAddressDetailResponseDto {
    private Long deliveryAddressId;
    private String addressName;
    private String recipient;
    private String phoneNumber;
    private String zip;
    private String post;
    private String street;
    private boolean defaultCheck;

    public static DeliveryAddressDetailResponseVo dtoToVo(DeliveryAddressDetailResponseDto deliveryAddressDetail) {
        DeliveryAddressDetailResponseVo vo = new DeliveryAddressDetailResponseVo(
                deliveryAddressDetail.getDeliveryAddressId(),
                deliveryAddressDetail.getAddressName(),
                deliveryAddressDetail.getRecipient(),
                deliveryAddressDetail.getPhoneNumber(),
                deliveryAddressDetail.getZip(),
                deliveryAddressDetail.getPost(),
                deliveryAddressDetail.getStreet(),
                deliveryAddressDetail.isDefaultCheck()
        );
        return vo;


    }
}
