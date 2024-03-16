package com.nocaffeine.ssgclone.cart.application;


import com.nocaffeine.ssgclone.cart.dto.request.CartRemoveListRequest;
import com.nocaffeine.ssgclone.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface CartService {

    ResponseDto<Void> addCart(Long productOptionId, String memberUuid);
    ResponseDto<Void> removeCart(CartRemoveListRequest cartRemoveListRequest, String memberUuid);

}
