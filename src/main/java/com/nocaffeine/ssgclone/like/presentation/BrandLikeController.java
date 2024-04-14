package com.nocaffeine.ssgclone.like.presentation;


import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.like.application.BrandLikeService;
import com.nocaffeine.ssgclone.like.dto.request.BrandLikeRemoveRequest;
import com.nocaffeine.ssgclone.like.dto.request.BrandLikeAddRequest;
import com.nocaffeine.ssgclone.like.dto.response.BrandLikeListResponse;
import com.nocaffeine.ssgclone.like.dto.response.LikeStatusResponseDto;
import com.nocaffeine.ssgclone.like.vo.response.BrandLikeStatusVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Like", description = "좋아요")
@RequestMapping("/api/v1/like/brand")
public class BrandLikeController {

    private final BrandLikeService brandLikeService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "브랜드 좋아요 추가", description = "브랜드 좋아요 추가")
    @PostMapping
    public CommonResponse<String> addBrandLike(@RequestBody BrandLikeAddRequest brandLikeAddRequest){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        brandLikeService.addBrandLike(brandLikeAddRequest, memberUuid);
        return CommonResponse.success("브랜드 좋아요 성공");
    }

    @Operation(summary = "브랜드 좋아요 취소", description = "브랜드 좋아요 취소")
    @DeleteMapping
    public CommonResponse<String> removeBrandLike(@RequestBody BrandLikeRemoveRequest brandLikeRemoveRequest){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        brandLikeService.removeBrandLike(brandLikeRemoveRequest, memberUuid);
        return CommonResponse.success("브랜드 좋아요 취소 성공");
    }

    @Operation(summary = "브랜드 좋아요 조회", description = "브랜드 좋아요 조회")
    @GetMapping
    public CommonResponse<List<BrandLikeListResponse>> brandLikeList(){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        return CommonResponse.success("브랜드 좋아요 조회 성공", brandLikeService.findBrandLike(memberUuid));
    }

    @Operation(summary = "브랜드 좋아요 여부", description = "브랜드 좋아요 여부")
    @GetMapping("/{brandId}")
    public CommonResponse<BrandLikeStatusVo> isBrandLike(@PathVariable("brandId") Long brandId){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        return CommonResponse.success("브랜드 좋아요 여부 조회 성공", LikeStatusResponseDto.dtoToBrandVo(brandLikeService.isBrandLike(brandId, memberUuid)));
    }
}

