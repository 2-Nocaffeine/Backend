package com.nocaffeine.ssgclone.cart.dto.response;

import com.nocaffeine.ssgclone.cart.vo.response.CartPriceResponseVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartPriceResponseDto {

    private int quantity;
    private int totalPrice;

    public static CartPriceResponseVo dtoToVo(CartPriceResponseDto cartPriceResponseDto) {
        return new CartPriceResponseVo(cartPriceResponseDto.getQuantity(), cartPriceResponseDto.getTotalPrice());
    }
}
