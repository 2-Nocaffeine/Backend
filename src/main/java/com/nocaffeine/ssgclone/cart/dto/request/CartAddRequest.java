package com.nocaffeine.ssgclone.cart.dto.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartAddRequest {

    private Long productOptionId;
    private int quantity;

}
