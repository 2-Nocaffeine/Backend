package com.nocaffeine.ssgclone.cart.application;


import com.nocaffeine.ssgclone.cart.domain.Cart;
import com.nocaffeine.ssgclone.cart.dto.request.CartAddRequestDto;
import com.nocaffeine.ssgclone.cart.dto.response.CartCountResponseDto;
import com.nocaffeine.ssgclone.cart.dto.response.CartListResponseDto;
import com.nocaffeine.ssgclone.cart.dto.response.CartPriceResponseDto;
import com.nocaffeine.ssgclone.cart.infrastructure.CartRepository;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import com.nocaffeine.ssgclone.product.domain.OptionSelectedProduct;
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
    private final OptionSelectedProductRepository optionSelectedProductRepository;

    /**
     * 장바구니 상품 추가.
     */
    @Override
    @Transactional
    public void addCart(CartAddRequestDto cartAddRequestDto, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        OptionSelectedProduct optionSelectedProduct = optionSelectedProductRepository.findById(cartAddRequestDto.getOptionSelectedProductId())
                .orElseThrow(() -> new BaseException(NO_SELECTED_OPTION_PRODUCT));

        Optional<Cart> memberCart = cartRepository.findByMemberAndOptionSelectedProduct(member, optionSelectedProduct);

        if (memberCart.isPresent()) {
            // 이미 장바구니에 해당 상품이 존재하는 경우 -> 수량만큼 추가
            Cart cart = memberCart.get();
            cartRepository.save(Cart.builder()
                    .id(cart.getId())
                    .member(member)
                    .optionSelectedProduct(optionSelectedProduct)
                    .quantity(cart.getQuantity() + cartAddRequestDto.getQuantity())
                    .pin(cart.isPin())
                    .checkProduct(cart.isCheckProduct())
                    .build()
            );
        } else {
            // 장바구니에 해당 상품이 없는경우
            cartRepository.save(Cart.builder()
                    .member(member)
                    .optionSelectedProduct(optionSelectedProduct)
                    .quantity(cartAddRequestDto.getQuantity())
                    .pin(false)
                    .checkProduct(false)
                    .build()
            );
        }
    }

    /**
     * 장바구니 리스트 조회.
     */
    @Override
    public List<CartListResponseDto> findCart(String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        List<Cart> cartList = cartRepository.findByMember(member);

        List<CartListResponseDto> responseCartList = new ArrayList<>();

        for (Cart cart : cartList) {
            CartListResponseDto response = CartListResponseDto.builder()
                    .cartId(cart.getId())
                    .productId(cart.getOptionSelectedProduct().getProduct().getId())
                    .optionSelectedProduct(cart.getOptionSelectedProduct().getId())
                    .quantity(cart.getQuantity())
                    .pin(cart.isPin())
                    .checkProduct(cart.isCheckProduct())
                    .build();
            responseCartList.add(response);
        }
        return responseCartList;
    }

    /**
     * 장바구니 선택한 상품 조회.
     */
    @Override
    public List<CartListResponseDto> findCheckedCart(String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        List<Cart> cartList = cartRepository.findByCheckProductAndMember(true, member);

        List<CartListResponseDto> responseCartList = new ArrayList<>();

        for (Cart cart : cartList) {
            CartListResponseDto response = CartListResponseDto.builder()
                    .cartId(cart.getId())
                    .productId(cart.getOptionSelectedProduct().getProduct().getId())
                    .optionSelectedProduct(cart.getOptionSelectedProduct().getId())
                    .quantity(cart.getQuantity())
                    .pin(cart.isPin())
                    .checkProduct(cart.isCheckProduct())
                    .build();
            responseCartList.add(response);
        }

        return responseCartList;
    }

    /**
     * 장바구니 상품 선택 삭제.
     */
    @Override
    @Transactional
    public void removeCartList(List<Long> cartId) {
        cartRepository.deleteAllById(cartId);
    }

    /**
     * 장바구니 상품 개수 조회.
     */
    @Override
    public CartCountResponseDto totalCountCart(String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        List<Cart> cartList = cartRepository.findByMember(member);

        int cartSize = cartList.size();

        return CartCountResponseDto.builder()
                .cartCount(cartSize)
                .build();

    }

    /**
     * 장바구니 상품 단건 삭제.
     */
    @Override
    @Transactional
    public void removeCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }



    /**
     * 장바구니 상품 수량 증가.
     */
    @Override
    @Transactional
    public void increaseCount(Long cartId){
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new BaseException(NO_DATA));

        cartRepository.save(Cart.builder()
                .id(cart.getId())
                .member(cart.getMember())
                .optionSelectedProduct(cart.getOptionSelectedProduct())
                .quantity(cart.plusQuantity())
                .pin(cart.isPin())
                .checkProduct(cart.isCheckProduct())
                .build());
    }

    /**
     * 장바구니 상품 수량 감소.
     */
    @Override
    @Transactional
    public void decreaseCount(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new BaseException(NO_DATA));

        cartRepository.save(Cart.builder()
                .id(cart.getId())
                .member(cart.getMember())
                .optionSelectedProduct(cart.getOptionSelectedProduct())
                .quantity(cart.minusQuantity())
                .pin(cart.isPin())
                .checkProduct(cart.isCheckProduct())
                .build());
    }

    /**
     * 장바구니 상품 선택.
     */
    @Override
    @Transactional
    public void checkCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new BaseException(NO_DATA));

        cartRepository.save(Cart.builder()
                .id(cart.getId())
                .member(cart.getMember())
                .optionSelectedProduct(cart.getOptionSelectedProduct())
                .quantity(cart.getQuantity())
                .pin(cart.isPin())
                .checkProduct(true)
                .build());
    }

    /**
     * 장바구니 상품 선택 해제.
     */
    @Override
    @Transactional
    public void unCheckCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new BaseException(NO_DATA));

        cartRepository.save(Cart.builder()
                .id(cart.getId())
                .member(cart.getMember())
                .optionSelectedProduct(cart.getOptionSelectedProduct())
                .quantity(cart.getQuantity())
                .pin(cart.isPin())
                .checkProduct(false)
                .build());
    }


    /**
     * 장바구니 선택한 상품 가격 조회.
     */
    @Override
    public CartPriceResponseDto findTotalPrice(List<Long> cartId) {
       int totalPrice = 0;
       int quantity = 0;
        for (Long cart : cartId) {
            Cart findCart = cartRepository.findById(cart)
                    .orElseThrow(() -> new BaseException(NO_DATA));

            totalPrice += findCart.getOptionSelectedProduct().getProduct().getPrice() * findCart.getQuantity();
            quantity += findCart.getQuantity();
        }

        return CartPriceResponseDto.builder()
                .quantity(quantity)
                .totalPrice(totalPrice)
                .build();
    }

}
