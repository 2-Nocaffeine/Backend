package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.product.application.ProductIdListWithCategoryService;
import com.nocaffeine.ssgclone.product.dto.response.ProductIdListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product/")
public class ProductIdListWithCategoryController {

    private final ProductIdListWithCategoryService productIdListWithCategoryService;

    //대분류별 상품 조회
    @GetMapping("/large/{largeId}/")
    public CommonResponse<List<ProductIdListResponse>> ProductIdWithLargeCategoryList(@PathVariable("largeId") Long largeId){
        List<ProductIdListResponse> productIdListResponseList = productIdListWithCategoryService.getProductIdListWithLargeCategory(largeId);
        return CommonResponse.success("해당 카테고리의 productIdList를 성공적으로 찾았습니다.",productIdListResponseList);
    }

    //중분류별 상품 조회
    @GetMapping("/medium/{mediumId}/")
    public CommonResponse<List<ProductIdListResponse>> ProductIdWithMediumCategoryList(@PathVariable("mediumId") Long mediumId){
        List<ProductIdListResponse> productIdListResponseList = productIdListWithCategoryService.getProductIdListWithMediumCategory(mediumId);
        return CommonResponse.success("해당 카테고리의 productIdList를 성공적으로 찾았습니다.",productIdListResponseList);
    }

    //소분류별 상품 조회
    @GetMapping("/small/{smallId}/")
    public CommonResponse<List<ProductIdListResponse>> ProductIdWithSmallCategoryList(@PathVariable("smallId") Long smallId){
        List<ProductIdListResponse> productIdListResponseList = productIdListWithCategoryService.getProductIdListWithSmallCategory(smallId);
        return CommonResponse.success("해당 카테고리의 productIdList를 성공적으로 찾았습니다.",productIdListResponseList);
    }

    //소소분류별 상품 조회
    @GetMapping("/tiny/{tinyId}/")
    public CommonResponse<List<ProductIdListResponse>> ProductIdWithTinyCategoryList(@PathVariable("tinyId") Long tinyId){
        List<ProductIdListResponse> productIdListResponseList = productIdListWithCategoryService.getProductIdListWithTinyCategory(tinyId);
        return CommonResponse.success("해당 카테고리의 productIdList를 성공적으로 찾았습니다.",productIdListResponseList);
    }
}
