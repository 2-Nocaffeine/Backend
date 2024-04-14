package com.nocaffeine.ssgclone.deliveryaddress.vo.response;

import lombok.Getter;

@Getter
public class DeliveryAddressDetailResponseVo {
    private Long deliveryAddressId;
    private String addressName;
    private String recipient;
    private String phoneNumber;
    private String zip;
    private String post;
    private String street;
    private boolean defaultCheck;

    public DeliveryAddressDetailResponseVo(Long deliveryAddressId, String addressName, String recipient, String phoneNumber, String zip, String post, String street, boolean defaultCheck) {
        this.deliveryAddressId = deliveryAddressId;
        this.addressName = addressName;
        this.recipient = recipient;
        this.phoneNumber = phoneNumber;
        this.zip = zip;
        this.post = post;
        this.street = street;
        this.defaultCheck = defaultCheck;
    }
}
