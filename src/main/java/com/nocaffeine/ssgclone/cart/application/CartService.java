package com.nocaffeine.ssgclone.cart.application;


import com.nocaffeine.ssgclone.cart.dto.request.CartAddRequestDto;
import com.nocaffeine.ssgclone.cart.dto.request.CartModifyRequestDto;
import com.nocaffeine.ssgclone.cart.dto.request.CartRemoveListRequestDto;
import com.nocaffeine.ssgclone.cart.dto.response.CartCountResponseDto;
import com.nocaffeine.ssgclone.cart.dto.response.CartListResponseDto;
import com.nocaffeine.ssgclone.cart.dto.response.CartPriceResponseDto;

import java.util.List;


public interface CartService {

    void addCart(CartAddRequestDto cartAddRequestDto, String memberUuid);
    void removeCart(CartRemoveListRequestDto cartRemoveRequestDto, String memberUuid);

    List<CartListResponseDto> findCart(String memberUuid);

    void modifyCart(CartModifyRequestDto cartModifyRequestDto);

    CartCountResponseDto totalCountCart(String memberUuid);

    CartPriceResponseDto findTotalPrice(List<Long> cartId);


}
