package com.nocaffeine.ssgclone.like.presentation;


import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.like.application.LikeService;
import com.nocaffeine.ssgclone.like.dto.request.LikeProductRequest;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LikeController {

    private final LikeService likeService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "상품 찜하기", description = "상품 찜하기", tags = {"Product Like"})
    @PostMapping("/like/product")
    public CommonResponse<String> addProductLike(@RequestBody LikeProductRequest likeProductRequest){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        likeService.addProductLike(likeProductRequest, memberUuid);
        return CommonResponse.success("찜하기 성공");


    }


}
