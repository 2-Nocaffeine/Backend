package com.nocaffeine.ssgclone.cart.application;


import com.nocaffeine.ssgclone.cart.domain.Cart;
import com.nocaffeine.ssgclone.cart.dto.request.CartRemoveListRequest;
import com.nocaffeine.ssgclone.cart.infrastructure.CartRepository;
import com.nocaffeine.ssgclone.common.ResponseDto;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.common.exception.BaseResponseStatus;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import com.nocaffeine.ssgclone.product.domain.ProductOption;
import com.nocaffeine.ssgclone.product.infrastructure.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.MEMBERS_STATUS_IS_NOT_FOUND;
import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_EXIST_MEMBERS;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CartServiceImp implements CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProductOptionRepository productOptionRepository;

    /**
     * 장바구니 상품 추가.
     */
    @Override
    @Transactional
    public ResponseDto<Void> addCart(Long productOptionId, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(MEMBERS_STATUS_IS_NOT_FOUND));

        ProductOption productOption = productOptionRepository.findById(productOptionId)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        Cart cart = Cart.builder()
                .member(member)
                .productOption(productOption)
                .quantity(1) // 최초 수량은 1개
                .pin(false)
                .checkProduct(false)
                .build();

        cartRepository.save(cart);

        return ResponseDto.success("장바구니에 상품을 추가하였습니다.");
    }

    /**
     * 장바구니 선택 상품 삭제.
     */
    @Override
    @Transactional
    public ResponseDto<Void> removeCart(CartRemoveListRequest cartRemoveListRequest, String memberUuid) {
        List<Long> cartIds = cartRemoveListRequest.getCartId();
        for (Long cartId : cartIds) {
            cartRepository.deleteById(cartId);
        }
        return ResponseDto.success("장바구니에서 상품을 삭제하였습니다.");
    }


}
