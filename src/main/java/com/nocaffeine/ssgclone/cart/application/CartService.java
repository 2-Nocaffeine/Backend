package com.nocaffeine.ssgclone.cart.application;


import com.nocaffeine.ssgclone.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface CartService {

    ResponseDto<Void> addCart(Long productId, String memberUuid);

}
