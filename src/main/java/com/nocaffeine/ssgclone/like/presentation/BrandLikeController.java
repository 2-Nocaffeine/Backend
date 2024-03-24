package com.nocaffeine.ssgclone.like.presentation;


import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.like.application.BrandLikeService;
import com.nocaffeine.ssgclone.like.dto.request.BrandLikeRemoveRequest;
import com.nocaffeine.ssgclone.like.dto.request.LikeBrandAddRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/like/brand")
public class BrandLikeController {

    private final BrandLikeService brandLikeService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public CommonResponse<String> addBrandLike(@RequestBody LikeBrandAddRequest likeBrandAddRequest){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        brandLikeService.addBrandLike(likeBrandAddRequest, memberUuid);
        return CommonResponse.success("브랜드 좋아요 성공");
    }

    @DeleteMapping
    public CommonResponse<String> removeBrandLike(@RequestBody BrandLikeRemoveRequest brandLikeRemoveRequest){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        brandLikeService.removeBrandLike(brandLikeRemoveRequest, memberUuid);
        return CommonResponse.success("브랜드 좋아요 취소 성공");
    }

}

