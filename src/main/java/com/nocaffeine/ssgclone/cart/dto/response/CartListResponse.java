package com.nocaffeine.ssgclone.cart.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartListResponse {
    private Long cartId;
    private Long productId;
    private Long productOptionId;
    private int quantity; // 장바구니에 담은 상품 수

}
