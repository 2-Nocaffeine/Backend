package com.nocaffeine.ssgclone.cart.dto.response;

import com.nocaffeine.ssgclone.cart.vo.response.CartListResponseVo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartListResponseDto {
    private Long cartId;
    private Long productId;
    private Long optionSelectedProduct;
    private int quantity; // 장바구니에 담은 상품 수


    public static CartListResponseVo dtoToVo(CartListResponseDto cartListResponseDto) {
        return new CartListResponseVo(cartListResponseDto.getCartId(), cartListResponseDto.getProductId(),
                cartListResponseDto.getOptionSelectedProduct(), cartListResponseDto.getQuantity());

    }
}
