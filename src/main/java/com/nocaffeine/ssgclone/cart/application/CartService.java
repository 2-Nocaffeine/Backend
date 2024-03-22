package com.nocaffeine.ssgclone.cart.application;


import com.nocaffeine.ssgclone.cart.dto.request.CartAddRequest;
import com.nocaffeine.ssgclone.cart.dto.request.CartModifyRequest;
import com.nocaffeine.ssgclone.cart.dto.request.CartRemoveListRequest;
import com.nocaffeine.ssgclone.cart.dto.response.CartCountResponse;
import com.nocaffeine.ssgclone.cart.dto.response.CartListResponse;
import com.nocaffeine.ssgclone.cart.dto.response.CartPriceResponse;

import java.util.List;


public interface CartService {

    void addCart(CartAddRequest cartAddRequest, String memberUuid);
    void removeCart(CartRemoveListRequest cartRemoveListRequest, String memberUuid);

    List<CartListResponse> listCart(String memberUuid);

    void modifyCart(CartModifyRequest cartModifyRequest);

    CartCountResponse countCart(String memberUuid);

    CartPriceResponse totalPrice(List<Long> cartId);


}
