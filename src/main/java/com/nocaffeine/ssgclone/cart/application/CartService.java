package com.nocaffeine.ssgclone.cart.application;


import com.nocaffeine.ssgclone.cart.dto.request.CartAddRequestDto;
import com.nocaffeine.ssgclone.cart.dto.response.CartCountResponseDto;
import com.nocaffeine.ssgclone.cart.dto.response.CartListResponseDto;
import com.nocaffeine.ssgclone.cart.dto.response.CartPriceResponseDto;

import java.util.List;


public interface CartService {

    void addCart(CartAddRequestDto cartAddRequestDto, String memberUuid);

    void removeCartList(List<Long> cartId);
    void removeCart(Long cartId);

    void increaseCount(Long cartId);
    void decreaseCount(Long cartId);

    void checkCart(Long cartId);
    void unCheckCart(Long cartId);

    List<CartListResponseDto> findCart(String memberUuid);
    List<CartListResponseDto> findCheckedCart(String memberUuid);

    CartCountResponseDto totalCountCart(String memberUuid);
    CartPriceResponseDto findTotalPrice(List<Long> cartId);


}
