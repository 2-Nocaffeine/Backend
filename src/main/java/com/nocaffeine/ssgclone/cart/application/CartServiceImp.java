package com.nocaffeine.ssgclone.cart.application;


import com.nocaffeine.ssgclone.cart.domain.Cart;
import com.nocaffeine.ssgclone.cart.dto.request.CartAddRequest;
import com.nocaffeine.ssgclone.cart.dto.request.CartRemoveListRequest;
import com.nocaffeine.ssgclone.cart.infrastructure.CartRepository;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import com.nocaffeine.ssgclone.product.domain.ProductOption;
import com.nocaffeine.ssgclone.product.infrastructure.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.*;

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
    public void addCart(CartAddRequest cartAddRequest, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        ProductOption productOption = productOptionRepository.findById(cartAddRequest.getProductOptionId())
                .orElseThrow(() -> new BaseException(NO_SELECTED_OPTION_PRODUCT));

        Optional<Cart> memberCart = cartRepository.findByMemberAndProductOption(member, productOption);

        if(memberCart.isPresent()) {
            // 이미 장바구니에 해당 상품이 존재하는 경우 -> 수량만큼 추가
            memberCart.get().addQuantity(cartAddRequest.getQuantity());
        } else{
            // 장바구니에 해당 상품이 없는경우
            Cart cart = Cart.builder()
                    .member(member)
                    .productOption(productOption)
                    .quantity(cartAddRequest.getQuantity())
                    .pin(false)
                    .checkProduct(false)
                    .build();

            cartRepository.save(cart);
        }
    }



    /**
     * 장바구니 선택 상품 삭제.
     */
    @Override
    @Transactional
    public void removeCart(CartRemoveListRequest cartRemoveListRequest, String memberUuid) {
        List<Long> cartIds = cartRemoveListRequest.getCartId();
        for (Long cartId : cartIds) {
            cartRepository.deleteById(cartId);
        }
    }


}
