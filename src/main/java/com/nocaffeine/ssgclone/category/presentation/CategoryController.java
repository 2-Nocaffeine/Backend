package com.nocaffeine.ssgclone.category.presentation;

import com.nocaffeine.ssgclone.category.application.CategoryService;
import com.nocaffeine.ssgclone.category.domain.ProductList;
import com.nocaffeine.ssgclone.category.dto.response.*;
import com.nocaffeine.ssgclone.common.CommonResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;


    //중분류 조회
    @GetMapping("/mid-category/{large_id}")
    public CommonResponse<List<MediumCategoryResponse>> getMidCategory(@PathVariable Long large_id) {
        List<MediumCategoryResponse> mediumCategoryDtoList = categoryService.findLargetoMedium(large_id);
        return CommonResponse.success("성공", mediumCategoryDtoList);
    }
    // 소분류 조회
    @GetMapping("/large-category/{medium_id}")
    public CommonResponse<List<SmallCategoryResponse>> getSmallCategory(@PathVariable Long medium_id) {
        List<SmallCategoryResponse> smallCategoryDtoList = categoryService.findMediumtoSmall(medium_id);
        return CommonResponse.success("성공", smallCategoryDtoList);
    }
    // 소분류 + 소소분류
    @GetMapping("/api/v1/category/medium/{small_id}")
    public CommonResponse<List<TinyCategoryResponse>> getTinycategory(@PathVariable("small_id") Long small_id) {
        List<TinyCategoryResponse> tinyCategoryDtoList = categoryService.findSmalltoTiny(small_id);
        return CommonResponse.success("성공", tinyCategoryDtoList);
    }




}
