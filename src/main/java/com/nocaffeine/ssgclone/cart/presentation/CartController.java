package com.nocaffeine.ssgclone.cart.presentation;


import com.nocaffeine.ssgclone.cart.application.CartService;
import com.nocaffeine.ssgclone.cart.dto.request.CartRemoveListRequest;
import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CartController {

    private final CartService cartService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/cart")
    public CommonResponse<Void> addCart(String token, Long productOptionId) {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        return cartService.addCart(productOptionId, memberUuid);
    }

    @DeleteMapping("/cart")
    public CommonResponse<Void> removeCart(String token, CartRemoveListRequest cartRemoveListRequest) {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        return cartService.removeCart(cartRemoveListRequest, memberUuid);
    }
}
