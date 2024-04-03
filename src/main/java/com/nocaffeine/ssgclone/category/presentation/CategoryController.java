package com.nocaffeine.ssgclone.category.presentation;

import com.nocaffeine.ssgclone.category.application.CategoryService;
import com.nocaffeine.ssgclone.category.dto.response.*;
import com.nocaffeine.ssgclone.common.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    //대분류 조회
    @Operation(summary = "대카테고리 조회", description = "대카테고리 조회", tags = {"Category"})
    @GetMapping("/large")
    public CommonResponse<List<LargeCategoryResponse>> largeCategoryList(){
;
        return CommonResponse.success("LargeCategory를 성공적으로 찾았습니다.", categoryService.findLargeCategories());
    }

    //중분류 조회
    @Operation(summary = "중카테고리 조회", description = "중카테고리 조회", tags = {"Category"})
    @GetMapping("/{largeId}/medium")
    public CommonResponse<List<MediumCategoryResponse>> midCategoryList(@PathVariable Long largeId) {
;
        return CommonResponse.success("MediumCategory를 성공적으로 찾았습니다.", categoryService.findMediumCategories(largeId));
    }
    // 소분류 조회
    @Operation(summary = "소카테고리 조회", description = "소카테고리 조회", tags = {"Category"})
    @GetMapping("/{mediumId}/small")
    public CommonResponse<List<SmallCategoryResponse>> smallCategoryList(@PathVariable Long mediumId) {

        return CommonResponse.success("SmallCategory를 성공적으로 찾았습니다.", categoryService.findSmallCategories(mediumId));
    }
    // 소분류 + 소소분류
    @Operation(summary = "소소카테고리 조회", description = "소소카테고리 조회", tags = {"Category"})
    @GetMapping("/{smallId}/tiny")
    public CommonResponse<List<TinyCategoryResponse>> tinyCategoryList(@PathVariable("smallId") Long smallId) {

        return CommonResponse.success("TinyCategory를 성공적으로 찾았습니다.", categoryService.findTinyCategories(smallId));
    }




}
