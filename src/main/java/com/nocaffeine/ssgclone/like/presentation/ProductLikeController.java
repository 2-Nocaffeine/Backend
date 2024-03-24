package com.nocaffeine.ssgclone.like.presentation;


import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.like.application.ProductLikeService;
import com.nocaffeine.ssgclone.like.dto.request.LikeProductAddRequest;
import com.nocaffeine.ssgclone.like.dto.request.LikeProductRemoveRequest;
import com.nocaffeine.ssgclone.like.dto.response.ProductLikeListResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/like/product")
public class ProductLikeController {

    private final ProductLikeService productLikeService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "상품 좋아요", description = "상품 좋아요", tags = {"Product Like"})
    @PostMapping
    public CommonResponse<String> addProductLike(@RequestBody LikeProductAddRequest likeProductAddRequest){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        productLikeService.addProductLike(likeProductAddRequest, memberUuid);
        return CommonResponse.success("좋아요 성공");
    }

    @Operation(summary = "상품 좋아요 취소", description = "상품 좋아요 취소", tags = {"Product Like"})
    @DeleteMapping
    public CommonResponse<String> removeProductLike(@RequestBody LikeProductRemoveRequest likeProductRemoveRequest){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        productLikeService.removeProductLike(likeProductRemoveRequest, memberUuid);
        return CommonResponse.success("좋아요 취소 성공");
    }

    @Operation(summary = "상품 좋아요 목록 조회", description = "상품 좋아요 목록 조회", tags = {"Product Like"})
    @GetMapping
    public CommonResponse<List<ProductLikeListResponse>> findProductLike(){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        return CommonResponse.success("좋아요 목록 조회 성공", productLikeService.findProductLike(memberUuid));
    }


}
