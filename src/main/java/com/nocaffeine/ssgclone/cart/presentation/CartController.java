package com.nocaffeine.ssgclone.cart.presentation;


import com.nocaffeine.ssgclone.cart.application.CartService;
import com.nocaffeine.ssgclone.cart.dto.request.CartAddRequest;
import com.nocaffeine.ssgclone.cart.dto.request.CartRemoveListRequest;
import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CartController {

    private final CartService cartService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/cart")
    public CommonResponse<String> addCart(String token, @RequestBody CartAddRequest cartAddRequest) {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        cartService.addCart(cartAddRequest, memberUuid);
        return CommonResponse.success("장바구니에 상품이 추가되었습니다.");
    }

    @DeleteMapping("/cart")
    public CommonResponse<String> removeCart(String token, CartRemoveListRequest cartRemoveListRequest) {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        cartService.removeCart(cartRemoveListRequest, memberUuid);
        return CommonResponse.success("장바구니 상품이 삭제되었습니다.");
    }
}
