package com.nocaffeine.ssgclone.cart.presentation;


import com.nocaffeine.ssgclone.cart.application.CartServiceImp;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CartController {

    private final CartServiceImp cartServiceImp;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/cart")
    public void addCart(String token, Long productOptionId) {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        cartServiceImp.addCart(productOptionId, memberUuid);

    }
}
