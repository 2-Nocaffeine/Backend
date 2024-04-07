package com.nocaffeine.ssgclone.deliveryaddress.vo.request;

import lombok.Getter;

@Getter
public class DeliveryAddressModifyRequestVo {
    private Long deliveryAddressId;
    private String addressName;
    private String recipient;
    private String phoneNumber;
    private String zip;
    private String post;
    private String street;

}
