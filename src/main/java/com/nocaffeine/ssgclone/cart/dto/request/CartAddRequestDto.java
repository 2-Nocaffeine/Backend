package com.nocaffeine.ssgclone.cart.dto.request;


import com.nocaffeine.ssgclone.cart.vo.request.CartAddRequestVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartAddRequestDto {

    private Long optionSelectedProductId;
    private int quantity;

    public static CartAddRequestDto voToDto(CartAddRequestVo cartAddRequestVo) {
        return CartAddRequestDto.builder()
                .optionSelectedProductId(cartAddRequestVo.getOptionSelectedProductId())
                .quantity(cartAddRequestVo.getQuantity())
                .build();

    }
}
