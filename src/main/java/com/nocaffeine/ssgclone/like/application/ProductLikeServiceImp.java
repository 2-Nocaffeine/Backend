package com.nocaffeine.ssgclone.like.application;


import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.like.domain.ProductLike;
import com.nocaffeine.ssgclone.like.dto.request.ProductLikeAddRequest;
import com.nocaffeine.ssgclone.like.dto.request.ProductLikeRemoveRequest;
import com.nocaffeine.ssgclone.like.dto.response.ProductLikeListResponse;
import com.nocaffeine.ssgclone.like.infrastructure.ProductLikeRepository;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import com.nocaffeine.ssgclone.product.domain.Product;
import com.nocaffeine.ssgclone.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ProductLikeServiceImp implements ProductLikeService {

    private final MemberRepository memberRepository;
    private final ProductLikeRepository productLikeRepository;
    private final ProductRepository productRepository;

    /**
     * 상품 좋아요 하기
     */
    @Override
    @Transactional
    public void addProductLike(ProductLikeAddRequest productLikeAddRequest, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        Product product = productRepository.findById(productLikeAddRequest.getProductId())
                .orElseThrow(() -> new BaseException(NO_PRODUCT));

        if(productLikeRepository.findByMemberAndProduct(member, product).isPresent()){
            // 이미 좋아요 했을경우
            throw new BaseException(ALREADY_ADDED_WISH_PRODUCT);
        }

        ProductLike productLike = ProductLike.builder()
                .member(member)
                .product(product)
                .build();

        productLikeRepository.save(productLike);
    }

    /**
     * 상품 좋아요 취소
     */
    @Override
    @Transactional
    public void removeProductLike(ProductLikeRemoveRequest productLikeRemoveRequest, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        Product product = productRepository.findById(productLikeRemoveRequest.getProductId())
                .orElseThrow(() -> new BaseException(NO_PRODUCT));

        ProductLike productLike = productLikeRepository.findByMemberAndProduct(member, product)
                .orElseThrow(() -> new BaseException(NO_EXIST_WISH_PRODUCT));

        productLikeRepository.delete(productLike);

    }

    /**
     * 상품 좋아요 목록 조회
     */
    @Override
    public List<ProductLikeListResponse> findProductLike(String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid).orElseThrow(()
                -> new BaseException(NO_EXIST_MEMBERS));

        List<ProductLike> productLike = productLikeRepository.findByMember(member);

        List<ProductLikeListResponse> productLikeListResponses = new ArrayList<>();

        for (ProductLike like : productLike) {
            ProductLikeListResponse response = ProductLikeListResponse.builder()
                    .productId(like.getProduct().getId())
                    .build();

            productLikeListResponses.add(response);
        }

        return productLikeListResponses;
    }

}
