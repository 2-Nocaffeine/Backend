package com.nocaffeine.ssgclone.order.vo.request;

import lombok.Getter;

import java.util.List;

@Getter
public class UserOrderProductRequestVo {

    private List<OrderedProductRequestVo> orderProducts;
    private String region;
    private String name;
    private String orderName;
    private String phoneNumber;
    private String email;
    private int totalPrice;

}
