package com.nocaffeine.ssgclone.cart.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartTotalPriceResponse {

    private int quantity;
    private int totalPrice;
}
