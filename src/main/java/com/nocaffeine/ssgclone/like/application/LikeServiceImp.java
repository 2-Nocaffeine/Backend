package com.nocaffeine.ssgclone.like.application;


import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.like.domain.ProductLike;
import com.nocaffeine.ssgclone.like.dto.request.LikeProductRequest;
import com.nocaffeine.ssgclone.like.infrastructure.ProductLikeRepository;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import com.nocaffeine.ssgclone.product.domain.Product;
import com.nocaffeine.ssgclone.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LikeServiceImp implements LikeService {

    private final MemberRepository memberRepository;
    private final ProductLikeRepository productLikeRepository;
    private final ProductRepository productRepository;

    /**
     * 상품 좋아요 하기
     */
    @Override
    @Transactional
    public void addProductLike(LikeProductRequest likeProductRequest, String memberUuid) {
        log .info("addProductLike memberUuid : {}", memberUuid);

        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        Product product = productRepository.findById(likeProductRequest.getProductId())
                .orElseThrow(() -> new BaseException(NO_PRODUCT));

        if(productLikeRepository.findByMemberAndProduct(member, product).isPresent()){
            throw new BaseException(ALREADY_ADDED_WISH_PRODUCT);
        }

        ProductLike productLike = ProductLike.builder()
                .member(member)
                .product(product)
                .build();

        productLikeRepository.save(productLike);
    }
}
