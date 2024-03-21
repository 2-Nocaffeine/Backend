package com.nocaffeine.ssgclone.category.presentation;

import com.nocaffeine.ssgclone.category.application.CategoryService;
import com.nocaffeine.ssgclone.category.domain.ProductList;
import com.nocaffeine.ssgclone.category.dto.response.*;
import com.nocaffeine.ssgclone.common.CommonResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    //대분류 + 중분류
    @GetMapping("/api/v1/category/{large_id}")
    public CommonResponse<List<MediumCategoryResponse>> getMediumcategory(@PathVariable("large_id") Long large_id) {
        List<MediumCategoryResponse> mediumCategoryDtoList = categoryService.findLargetoMedium(large_id);
        return CommonResponse.success("성공", mediumCategoryDtoList);
    }
    // 중분류 + 소분류
    @GetMapping("/api/v1/category/large/{medium_id}")
    public CommonResponse<List<SmallCategoryResponse>> getSmallcategory(@PathVariable("medium_id") Long medium_id) {
        List<SmallCategoryResponse> smallCategoryDtoList = categoryService.findMediumtoSmall(medium_id);
        return CommonResponse.success("성공", smallCategoryDtoList);
    }
    // 소분류 + 소소분류
    @GetMapping("/api/v1/category/medium/{small_id}")
    public CommonResponse<List<TinyCategoryResponse>> getTinycategory(@PathVariable("small_id") Long small_id) {
        List<TinyCategoryResponse> tinyCategoryDtoList = categoryService.findSmalltoTiny(small_id);
        return CommonResponse.success("성공", tinyCategoryDtoList);
    }

    // 카테고리 + 상품
    // 대분류 + 상품(상품전체보기)
    // productlist 엔티티에서 large_id를 받아와서 해당하는 상품을 찾아온다.
    @GetMapping("/api/v1/product/category/{large_id}")
    public CommonResponse<List<ProductIdResponse>> findProductIdToLarge(@PathVariable("large_id") Long large_id) {
        List<ProductIdResponse> productListToLarge = categoryService.findProductIdToLarge(large_id);
        return CommonResponse.success("성공", productListToLarge);

    }

//    컴포넌트의 productList값 가져옴(상품명, 가격)
    @GetMapping("/api/v1/product/productList/{product_id}")
    public CommonResponse<ProductListResponse> findProductListValue(@PathVariable("product_id") Long product_id) {
        ProductListResponse productListvalue = categoryService.findProductListValueToLarge(product_id);
        return CommonResponse.success("성공", productListvalue);
    }



}
