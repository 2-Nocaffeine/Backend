package com.nocaffeine.ssgclone.like.presentation;


import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.common.security.JwtTokenProvider;
import com.nocaffeine.ssgclone.like.application.CategoryLikeService;
import com.nocaffeine.ssgclone.like.dto.request.CategoryLikeAddRequest;
import com.nocaffeine.ssgclone.like.dto.request.CategoryLikeRemoveRequest;
import com.nocaffeine.ssgclone.like.dto.response.CategoryLikeListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/like/category")
public class CategoryLikeController {

    private final CategoryLikeService categoryLikeService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public CommonResponse<String> addCategoryLike(@RequestBody CategoryLikeAddRequest categoryLikeAddRequest){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        categoryLikeService.addCategoryLike(categoryLikeAddRequest, memberUuid);
        return CommonResponse.success("카테고리 좋아요 성공");
    }

    @GetMapping
    public CommonResponse<List<CategoryLikeListResponse>> findCategoryLike(){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        return CommonResponse.success("카테고리 좋아요 조회 성공",categoryLikeService.findCategoryLike(memberUuid));
    }

    @DeleteMapping
    public CommonResponse<String> removeCategoryLike(@RequestBody CategoryLikeRemoveRequest categoryLikeRemoveRequest){
        String memberUuid = jwtTokenProvider.validateAndGetUserUuid(jwtTokenProvider.getHeader());
        categoryLikeService.removeCategoryLike(categoryLikeRemoveRequest, memberUuid);
        return CommonResponse.success("카테고리 좋아요 삭제 성공");
    }


}
