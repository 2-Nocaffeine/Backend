package com.nocaffeine.ssgclone.cart.vo.response;

import lombok.Getter;

@Getter
public class CartListResponseVo {
    private Long cartId;
    private Long productId;
    private Long optionSelectedProduct;
    private int quantity; // 장바구니에 담은 상품 수

    public CartListResponseVo(Long cartId, Long productId, Long optionSelectedProduct, int quantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.optionSelectedProduct = optionSelectedProduct;
        this.quantity = quantity;
    }
}
