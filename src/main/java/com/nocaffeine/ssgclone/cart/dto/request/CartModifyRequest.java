package com.nocaffeine.ssgclone.cart.dto.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartModifyRequest {
    private Long cartId;
    private String pm;
}
