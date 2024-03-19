package com.nocaffeine.ssgclone.cart.application;


import com.nocaffeine.ssgclone.cart.dto.request.CartAddRequest;
import com.nocaffeine.ssgclone.cart.dto.request.CartRemoveListRequest;
import com.nocaffeine.ssgclone.common.CommonResponse;


public interface CartService {

    void addCart(CartAddRequest cartAddRequest, String memberUuid);
    void removeCart(CartRemoveListRequest cartRemoveListRequest, String memberUuid);


}
