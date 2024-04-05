package com.nocaffeine.ssgclone.like.application;

import com.nocaffeine.ssgclone.brandstore.domain.Brand;
import com.nocaffeine.ssgclone.brandstore.infrastructure.BrandRepository;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.like.domain.BrandLike;
import com.nocaffeine.ssgclone.like.domain.ProductLike;
import com.nocaffeine.ssgclone.like.dto.request.BrandLikeRemoveRequest;
import com.nocaffeine.ssgclone.like.dto.request.BrandLikeAddRequest;
import com.nocaffeine.ssgclone.like.dto.response.BrandLikeListResponse;
import com.nocaffeine.ssgclone.like.dto.response.ProductLikeListResponse;
import com.nocaffeine.ssgclone.like.infrastructure.BrandLikeRepository;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
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
public class BrandLikeServiceImp implements BrandLikeService{

    private final MemberRepository memberRepository;
    private final BrandLikeRepository brandLikeRepository;
    private final BrandRepository brandRepository;


    /**
     * 브랜드 좋아요 하기
     */
    @Override
    @Transactional
    public void addBrandLike(BrandLikeAddRequest brandLikeAddRequest, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        Brand brand = brandRepository.findById(brandLikeAddRequest.getBrandId())
                .orElseThrow(() -> new BaseException(NO_EXIST_BRAND));

        if(brandLikeRepository.findByMemberAndBrand(member, brand).isPresent()){
            // 이미 좋아요 했을경우
            throw new BaseException(ALREADY_ADDED_WISH_BRAND);
        }

        BrandLike brandLike = BrandLike.builder()
                .member(member)
                .brand(brand)
                .build();

        brandLikeRepository.save(brandLike);
    }


    /**
     * 브랜드 좋아요 취소
     */
    @Override
    @Transactional
    public void removeBrandLike(BrandLikeRemoveRequest brandLikeRemoveRequest, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        Brand brand = brandRepository.findById(brandLikeRemoveRequest.getBrandId())
                .orElseThrow(() -> new BaseException(NO_EXIST_BRAND));

        BrandLike brandLike = brandLikeRepository.findByMemberAndBrand(member, brand)
                .orElseThrow(() -> new BaseException(NO_EXIST_WISH_BRAND));

        brandLikeRepository.delete(brandLike);
    }


    /**
     * 브랜드 좋아요 목록 조회
     */
    @Override
    public List<BrandLikeListResponse> findBrandLike(String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        List<BrandLike> bradList = brandLikeRepository.findByMember(member);

        List<BrandLikeListResponse> brandLikeListResponses = new ArrayList<>();


        for (BrandLike brandLike : bradList) {
            BrandLikeListResponse response = BrandLikeListResponse.builder()
                    .brandId(brandLike.getBrand().getId())
                    .brandName(brandLike.getBrand().getName())
                    .build();

            brandLikeListResponses.add(response);
        }

        return brandLikeListResponses;
    }

    @Override
    public boolean isBrandLike(Long brandId, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new BaseException(NO_EXIST_BRAND));

        return brandLikeRepository.findByMemberAndBrand(member, brand).isPresent();
    }


}
