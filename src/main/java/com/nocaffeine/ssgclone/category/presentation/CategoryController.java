package com.nocaffeine.ssgclone.category.presentation;

import com.nocaffeine.ssgclone.category.application.CategoryService;
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

    //대분류 조회
    @GetMapping("/large")
    public CommonResponse<List<LargeCategoryResponse>> LargeCategoryList(){
        List<LargeCategoryResponse> largecategoryList = categoryService.findLargeCategories();
        return CommonResponse.success("LargeCategory를 성공적으로 찾았습니다.", largecategoryList);
    }

    //중분류 조회
    @GetMapping("/{largeId}/medium")
    public CommonResponse<List<MediumCategoryResponse>> MidCategoryList(@PathVariable Long largeId) {
        List<MediumCategoryResponse> mediumCategoryDtoList = categoryService.findMediumCategories(largeId);
        return CommonResponse.success("MediumCategory를 성공적으로 찾았습니다.", mediumCategoryDtoList);
    }
    // 소분류 조회
    @GetMapping("/{mediumId}/small")
    public CommonResponse<List<SmallCategoryResponse>> SmallCategoryList(@PathVariable Long mediumId) {
        List<SmallCategoryResponse> smallCategoryDtoList = categoryService.findSmallCategories(mediumId);
        return CommonResponse.success("SmallCategory를 성공적으로 찾았습니다.", smallCategoryDtoList);
    }
    // 소분류 + 소소분류
    @GetMapping("/{smallId}/tiny")
    public CommonResponse<List<TinyCategoryResponse>> TinycategoryList(@PathVariable("smallId") Long smallId) {
        List<TinyCategoryResponse> tinyCategoryDtoList = categoryService.findSmalltoTiny(smallId);
        return CommonResponse.success("TinyCategory를 성공적으로 찾았습니다.", tinyCategoryDtoList);
    }




}
