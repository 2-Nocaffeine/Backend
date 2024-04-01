package com.nocaffeine.ssgclone.cart.dto.request;


import com.nocaffeine.ssgclone.cart.vo.request.CartRemoveRequestVo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartRemoveListRequestDto {
    private List<Long> cartId = new ArrayList<>();

    public static CartRemoveListRequestDto voToDto(CartRemoveRequestVo cartRemoveRequestVo) {
        return CartRemoveListRequestDto.builder()
                .cartId(cartRemoveRequestVo.getCartId())
                .build();

    }
}
