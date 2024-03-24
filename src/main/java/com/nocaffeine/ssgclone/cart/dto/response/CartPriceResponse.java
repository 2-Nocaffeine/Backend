package com.nocaffeine.ssgclone.cart.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartPriceResponse {

    private int quantity;
    private int totalPrice;
}
