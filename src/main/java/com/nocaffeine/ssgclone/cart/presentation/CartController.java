package com.nocaffeine.ssgclone.cart.presentation;


import com.nocaffeine.ssgclone.cart.application.CartService;
import com.nocaffeine.ssgclone.cart.dto.request.CartAddRequestDto;
import com.nocaffeine.ssgclone.cart.dto.request.CartModifyRequestDto;
import com.nocaffeine.ssgclone.cart.dto.request.CartRemoveListRequestDto;
import com.nocaffeine.ssgclone.cart.dto.response.CartCountResponseDto;
import com.nocaffeine.ssgclone.cart.dto.response.CartListResponseDto;
import com.nocaffeine.ssgclone.cart.dto.response.CartPriceResponseDto;
import com.nocaffeine.ssgclone.cart.vo.request.CartAddRequestVo;
import com.nocaffeine.ssgclone.cart.vo.request.CartModifyRequestVo;
import com.nocaffeine.ssgclone.cart.vo.request.CartRemoveRequestVo;
import com.nocaffeine.ssgclone.cart.vo.response.CartCountResponseVo;
import com.nocaffeine.ssgclone.cart.vo.response.CartListResponseVo;
import com.nocaffeine.ssgclone.cart.vo.response.CartPriceResponseVo;
import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public CommonResponse<String> cartAdd(@RequestBody CartAddRequestVo cartAddRequestVo) {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        cartService.addCart(CartAddRequestDto.voToDto(cartAddRequestVo), memberUuid);
        return CommonResponse.success("장바구니에 상품이 추가되었습니다.");
    }

    @Operation(summary = "장바구니에 상품 삭제", description = "장바구니에 상품 삭제", tags = {"Cart Remove"})
    @DeleteMapping("/cart")
    public CommonResponse<String> cartRemove(@RequestBody CartRemoveRequestVo cartRemoveRequestVo) {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        cartService.removeCart(CartRemoveListRequestDto.voToDto(cartRemoveRequestVo), memberUuid);
        return CommonResponse.success("장바구니 상품이 삭제되었습니다.");
    }

    @Operation(summary = "장바구니 조회", description = "장바구니 조회", tags = {"Cart List"})
    @GetMapping("/cart")
    public CommonResponse<List<CartListResponseVo>> cartList() {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());

        List<CartListResponseVo> cartListResponseVo = new ArrayList<>();
        for (CartListResponseDto cartListResponseDto : cartService.findCart(memberUuid)) {
            cartListResponseVo.add(CartListResponseDto.dtoToVo(cartListResponseDto));
        }

        return CommonResponse.success("장바구니 조회성공",cartListResponseVo);
    }

    @Operation(summary = "장바구니 상품 수정", description = "장바구니 상품 수정", tags = {"Cart Modify"})
    @PatchMapping("/cart")
    public CommonResponse<String> cartModify(@RequestBody CartModifyRequestVo cartModifyRequestVo) {
        cartService.modifyCart(CartModifyRequestDto.voToDto(cartModifyRequestVo));
        return CommonResponse.success("장바구니 상품이 수정되었습니다.");
    }

    @Operation(summary = "장바구니 상품 개수 조회", description = "장바구니 상품 개수 조회", tags = {"Cart Count"})
    @GetMapping("/cart/count")
    public CommonResponse<CartCountResponseVo> cartCount() {
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        return CommonResponse.success("장바구니 상품 개수 조회 성공", CartCountResponseDto.dtoToVo(cartService.totalCountCart(memberUuid)));
    }

    @Operation(summary = "장바구니 선택한 상품 가격 조회", description = "장바구니 선택한 상품 가격 조회", tags = {"Cart Price"})
    @GetMapping("/cart/price")
    public CommonResponse<CartPriceResponseVo> cartPrice(@RequestParam List<Long> cartId){
        return CommonResponse.success("장바구니 선택한 상품 가격 조회 성공", CartPriceResponseDto.dtoToVo(cartService.findTotalPrice(cartId)));
    }
}
