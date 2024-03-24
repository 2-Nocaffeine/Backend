package com.nocaffeine.ssgclone.cart.presentation;


import com.nocaffeine.ssgclone.cart.application.CartService;
import com.nocaffeine.ssgclone.cart.dto.request.CartAddRequest;
import com.nocaffeine.ssgclone.cart.dto.request.CartModifyRequest;
import com.nocaffeine.ssgclone.cart.dto.request.CartRemoveListRequest;
import com.nocaffeine.ssgclone.cart.dto.response.CartCountResponse;
import com.nocaffeine.ssgclone.cart.dto.response.CartListResponse;
import com.nocaffeine.ssgclone.cart.dto.response.CartPriceResponse;
import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CartController {

    private final CartService cartService;
    private final JwtTokenProvider jwtTokenProvider;


    @Operation(summary = "장바구니에 상품 추가", description = "장바구니에 상품 추가", tags = {"Cart Add"})
    @PostMapping("/cart")
    public CommonResponse<String> cartAdd(@RequestBody CartAddRequest cartAddRequest) {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        cartService.addCart(cartAddRequest, memberUuid);
        return CommonResponse.success("장바구니에 상품이 추가되었습니다.");
    }

    @Operation(summary = "장바구니에 상품 삭제", description = "장바구니에 상품 삭제", tags = {"Cart Remove"})
    @DeleteMapping("/cart")
    public CommonResponse<String> cartRemove(@RequestBody CartRemoveListRequest cartRemoveListRequest) {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        log.info(cartRemoveListRequest.getCartId().toString());
        cartService.removeCart(cartRemoveListRequest, memberUuid);
        return CommonResponse.success("장바구니 상품이 삭제되었습니다.");
    }

    @Operation(summary = "장바구니 조회", description = "장바구니 조회", tags = {"Cart List"})
    @GetMapping("/cart")
    public CommonResponse<List<CartListResponse>> cartList() {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        return CommonResponse.success("장바구니 조회성공", cartService.listCart(memberUuid));
    }

    @Operation(summary = "장바구니 상품 수정", description = "장바구니 상품 수정", tags = {"Cart Modify"})
    @PatchMapping("/cart")
    public CommonResponse<String> cartModify(@RequestBody CartModifyRequest cartModifyRequest) {
        cartService.modifyCart(cartModifyRequest);
        return CommonResponse.success("장바구니 상품이 수정되었습니다.");
    }

    @Operation(summary = "장바구니 상품 개수 조회", description = "장바구니 상품 개수 조회", tags = {"Cart Count"})
    @GetMapping("/cart/count")
    public CommonResponse<CartCountResponse> cartCount() {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        return CommonResponse.success("장바구니 상품 개수 조회 성공", cartService.countCart(memberUuid));
    }

    @Operation(summary = "장바구니 선택한 상품 가격 조회", description = "장바구니 선택한 상품 가격 조회", tags = {"Cart Price"})
    @GetMapping("/cart/price")
    public CommonResponse<CartPriceResponse> cartPrice(@RequestParam List<Long> cartId){
        return CommonResponse.success("장바구니 선택한 상품 가격 조회 성공", cartService.totalPrice(cartId));
    }
}
