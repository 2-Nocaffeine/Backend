package com.nocaffeine.ssgclone.like.presentation;


import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.like.application.ProductLikeService;
import com.nocaffeine.ssgclone.like.dto.request.ProductLikeAddRequest;
import com.nocaffeine.ssgclone.like.dto.request.ProductLikeListRequestDto;
import com.nocaffeine.ssgclone.like.dto.request.ProductLikeRemoveRequest;
import com.nocaffeine.ssgclone.like.dto.response.LikeStatusResponseDto;
import com.nocaffeine.ssgclone.like.dto.response.ProductLikeListResponse;
import com.nocaffeine.ssgclone.like.vo.request.ProductLikeListRequestVo;
import com.nocaffeine.ssgclone.like.vo.response.ProductLikeStatusVo;
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
@RequestMapping("/api/v1/like/product")
public class ProductLikeController {

    private final ProductLikeService productLikeService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "상품 좋아요 추가", description = "상품 좋아요 추가")
    @PostMapping
    public CommonResponse<String> addProductLike(@RequestBody ProductLikeAddRequest productLikeAddRequest){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        productLikeService.addProductLike(productLikeAddRequest, memberUuid);
        return CommonResponse.success("좋아요 성공");
    }

    @Operation(summary = "상품 좋아요 취소", description = "상품 좋아요 취소")
    @DeleteMapping
    public CommonResponse<String> removeProductLike(@RequestBody ProductLikeRemoveRequest productLikeRemoveRequest){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        productLikeService.removeProductLike(productLikeRemoveRequest, memberUuid);
        return CommonResponse.success("좋아요 취소 성공");
    }

    @Operation(summary = "상품 좋아요 여러개 취소", description = "상품 좋아요 여러개 취소")
    @DeleteMapping("/list")
    public CommonResponse<String> removeListProductLike(@RequestBody ProductLikeListRequestVo productLikeListRequestVo){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        productLikeService.removeListProductLike(ProductLikeListRequestDto.voToDto(productLikeListRequestVo), memberUuid);
        return CommonResponse.success("좋아요 여러개 취소 성공");
    }

    @Operation(summary = "상품 좋아요 조회", description = "상품 좋아요 조회")
    @GetMapping
    public CommonResponse<List<ProductLikeListResponse>> findProductLike(){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        return CommonResponse.success("좋아요 조회 성공", productLikeService.findProductLike(memberUuid));
    }

    @Operation(summary = "상품 좋아요 여부", description = "상품 좋아요 여부")
    @GetMapping("/{productId}")
    public CommonResponse<ProductLikeStatusVo> isProductLike(@PathVariable("productId") Long productId){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        return CommonResponse.success("좋아요 여부 조회 성공", LikeStatusResponseDto.dtoToProductVo(productLikeService.isProductLike(productId, memberUuid)));
    }


}
