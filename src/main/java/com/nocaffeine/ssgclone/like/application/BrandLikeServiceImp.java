package com.nocaffeine.ssgclone.like.application;

import com.nocaffeine.ssgclone.brandstore.domain.Brand;
import com.nocaffeine.ssgclone.brandstore.infrastructure.BrandRepository;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.like.domain.BrandLike;
import com.nocaffeine.ssgclone.like.dto.request.LikeBrandAddRequest;
import com.nocaffeine.ssgclone.like.infrastructure.BrandLikeRepository;
import com.nocaffeine.ssgclone.member.domain.Member;
import com.nocaffeine.ssgclone.member.infrastructure.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void addBrandLike(LikeBrandAddRequest likeBrandAddRequest, String memberUuid) {
        Member member = memberRepository.findByUuid(memberUuid)
                .orElseThrow(() -> new BaseException(NO_EXIST_MEMBERS));

        Brand brand = brandRepository.findById(likeBrandAddRequest.getBrandId())
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
}
