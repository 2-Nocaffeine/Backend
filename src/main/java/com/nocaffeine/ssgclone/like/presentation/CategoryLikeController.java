package com.nocaffeine.ssgclone.like.presentation;


import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.like.application.CategoryLikeService;
import com.nocaffeine.ssgclone.like.dto.request.CategoryLikeAddRequest;
import com.nocaffeine.ssgclone.like.dto.request.CategoryLikeRemoveRequest;
import com.nocaffeine.ssgclone.like.dto.response.CategoryLikeListResponse;
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
@RequestMapping("/api/v1/like/category")
public class CategoryLikeController {

    private final CategoryLikeService categoryLikeService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "카테고리 좋아요 추가", description = "카테고리 좋아요 추가")
    @PostMapping
    public CommonResponse<String> addCategoryLike(@RequestBody CategoryLikeAddRequest categoryLikeAddRequest){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        categoryLikeService.addCategoryLike(categoryLikeAddRequest, memberUuid);
        return CommonResponse.success("카테고리 좋아요 성공");
    }

    @Operation(summary = "카테고리 좋아요 조회", description = "카테고리 좋아요 조회")
    @GetMapping
    public CommonResponse<List<CategoryLikeListResponse>> findCategoryLike(){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        return CommonResponse.success("카테고리 좋아요 조회 성공",categoryLikeService.findCategoryLike(memberUuid));
    }

    @Operation(summary = "카테고리 좋아요 삭제", description = "카테고리 좋아요 삭제")
    @DeleteMapping
    public CommonResponse<String> removeCategoryLike(@RequestBody CategoryLikeRemoveRequest categoryLikeRemoveRequest){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        categoryLikeService.removeCategoryLike(categoryLikeRemoveRequest, memberUuid);
        return CommonResponse.success("카테고리 좋아요 삭제 성공");
    }



}
