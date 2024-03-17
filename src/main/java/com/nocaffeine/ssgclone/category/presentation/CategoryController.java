package com.nocaffeine.ssgclone.category.presentation;

import com.nocaffeine.ssgclone.category.application.CategoryService;
import com.nocaffeine.ssgclone.category.domain.LargeCategory;
import com.nocaffeine.ssgclone.category.domain.MediumCategory;
import com.nocaffeine.ssgclone.category.domain.SmallCategory;
import com.nocaffeine.ssgclone.category.domain.TinyCategory;
import com.nocaffeine.ssgclone.category.dto.LargeCategoryRequest;
import com.nocaffeine.ssgclone.category.dto.MediumCategoryRequest;
import com.nocaffeine.ssgclone.category.dto.SmallCategoryRequest;
import com.nocaffeine.ssgclone.common.ResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    //전체조회 + 대분류
//    @GetMapping("/api/v1/category/category")
//    public ResponseDto<List<LargeCategory>> getLargecategory() {
//        return categoryService.findLargeCategory();
//    }

    //대분류 + 중분류
    @GetMapping("/api/v1/category/")
    public ResponseDto<List<MediumCategory>> getMediumcategory(@RequestBody @Valid LargeCategoryRequest largeCategoryRequest) {
        return categoryService.findLargetoMedium(largeCategoryRequest);
    }
    //중분류 + 소분류
    @GetMapping("/api/v1/category/small")
    public ResponseDto<List<SmallCategory>> getSmallcategory(@RequestBody @Valid MediumCategoryRequest mediumCategoryRequest) {
        return categoryService.findMediumtoSmall(mediumCategoryRequest);
    }
    //중분류 + 소분류
    @GetMapping("/api/v1/category/tiny")
    public ResponseDto<List<TinyCategory>> getTinycategory(@RequestBody @Valid SmallCategoryRequest smallCategoryRequest) {
        return categoryService.findSmalltoTiny(smallCategoryRequest);
    }

}
