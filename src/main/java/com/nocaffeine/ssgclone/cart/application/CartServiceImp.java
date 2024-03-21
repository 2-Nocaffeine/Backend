package com.nocaffeine.ssgclone.cart.application;


import com.nocaffeine.ssgclone.cart.domain.Cart;
import com.nocaffeine.ssgclone.cart.dto.request.CartAddRequest;
import com.nocaffeine.ssgclone.cart.dto.request.CartModifyRequest;
import com.nocaffeine.ssgclone.cart.dto.request.CartRemoveListRequest;
import com.nocaffeine.ssgclone.cart.dto.response.CartListResponse;
import com.nocaffeine.ssgclone.cart.infrastructure.CartRepository;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import com.nocaffeine.ssgclone.product.domain.ProductOption;
import com.nocaffeine.ssgclone.product.infrastructure.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

        if (memberCart.isPresent()) {
            // 이미 장바구니에 해당 상품이 존재하는 경우 -> 수량만큼 추가
            memberCart.get().addQuantity(cartAddRequest.getQuantity());
        } else {
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
            cartRepository.findById(cartId).orElseThrow(()
                    -> new BaseException(NO_DATA));

            cartRepository.deleteById(cartId);
        }
    }

    /**
     * 장바구니 목록 조회.
     */
    @Override
    public List<CartListResponse> listCart(String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        List<Cart> cartList = cartRepository.findByMember(member);

        List<CartListResponse> responseCartList = new ArrayList<>();

        for (Cart cart : cartList) {
            CartListResponse response = CartListResponse.builder()
                    .cartId(cart.getId())
                    .productId(cart.getProductOption().getProduct().getId())
                    .quantity(cart.getQuantity())
                    .productOptionId(cart.getProductOption().getId())
                    .build();
            responseCartList.add(response);
        }
        return responseCartList;
    }

    /**
     * 장바구니 상품 수량 수정.
     */
    @Override
    @Transactional
    public void modifyCart(CartModifyRequest cartModifyRequest){
        Cart cart = cartRepository.findById(cartModifyRequest.getCartId())
                .orElseThrow(() -> new BaseException(NO_DATA));

        if(cartModifyRequest.getPm().equals("plus")){
            cart.plusQuantity();
        }else if(cartModifyRequest.getPm().equals("minus")){
            cart.minusQuantity();
        } else{
            throw new BaseException(NO_DATA);


        }
    }


}
