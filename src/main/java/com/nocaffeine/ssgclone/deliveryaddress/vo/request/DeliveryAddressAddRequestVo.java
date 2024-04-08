package com.nocaffeine.ssgclone.deliveryaddress.vo.request;

import lombok.Getter;

@Getter
public class DeliveryAddressAddRequestVo {
    private String addressName;
    private String recipient;
    private String phoneNumber;
    private String zip;
    private String post;
    private String street;
}
