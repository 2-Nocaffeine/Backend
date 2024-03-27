package com.nocaffeine.ssgclone.cart.vo.request;

import lombok.Getter;

@Getter
public class CartAddRequestVo {
    private Long optionSelectedProductId;
    private int quantity;
}
