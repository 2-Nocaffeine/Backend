package com.nocaffeine.ssgclone.cart.dto.response;

import com.nocaffeine.ssgclone.cart.vo.response.CartCountResponseVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartCountResponseDto {
    private int cartCount;

    public static CartCountResponseVo dtoToVo(CartCountResponseDto cartCountResponseDto) {
        return new CartCountResponseVo(cartCountResponseDto.getCartCount());

    }
}
