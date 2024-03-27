package com.nocaffeine.ssgclone.order.vo.request;

import lombok.Getter;

@Getter
public class OrderedProductVo {
    private Long optionSelectedProductId;
    private int count;
    private int price;
}
