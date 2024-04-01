package com.nocaffeine.ssgclone.cart.dto.request;


import com.nocaffeine.ssgclone.cart.vo.request.CartModifyRequestVo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartModifyRequestDto {
    private Long cartId;
    private String pm;

    public static CartModifyRequestDto voToDto(CartModifyRequestVo cartModifyRequestVo) {
        return CartModifyRequestDto.builder()
                .cartId(cartModifyRequestVo.getCartId())
                .pm(cartModifyRequestVo.getPm())
                .build();
    }
}
