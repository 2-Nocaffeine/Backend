package com.nocaffeine.ssgclone.cart.application;


import com.nocaffeine.ssgclone.cart.dto.request.CartRemoveListRequest;
import com.nocaffeine.ssgclone.common.CommonResponse;


public interface CartService {

    CommonResponse<Void> addCart(Long productOptionId, String memberUuid);
    CommonResponse<Void> removeCart(CartRemoveListRequest cartRemoveListRequest, String memberUuid);

}
