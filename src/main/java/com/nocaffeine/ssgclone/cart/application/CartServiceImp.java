package com.nocaffeine.ssgclone.cart.application;


import com.nocaffeine.ssgclone.cart.domain.Cart;
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

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CartServiceImp {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProductOptionRepository productOptionRepository;

    public void addCart(Long productOptionId, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException("존재하지 않는 회원입니다."));

        ProductOption productOption = productOptionRepository.findById(productOptionId)
                .orElseThrow(() -> new BaseException("존재하지 않는 상품입니다."));

        Cart cart = Cart.builder()
                .member(member)
                .productOption(productOption)
                .quantity(productOption.getQuantity())
                .pin(false)
                .checkProduct(false)
                .build();

        cartRepository.save(cart);
    }
}
