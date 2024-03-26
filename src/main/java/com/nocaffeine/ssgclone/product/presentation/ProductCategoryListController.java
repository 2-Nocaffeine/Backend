package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.product.application.ProductCategoryListService;
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
public class ProductCategoryListController {

    private final ProductCategoryListService productCategoryListService;

    //대분류별 상품 조회
    @GetMapping("/large/{largeId}/")
    public CommonResponse<List<ProductIdListResponse>> productIdWithLargeCategoryList(@PathVariable("largeId") Long largeId){
        List<ProductIdListResponse> productIdListResponseList = productCategoryListService.getProductIdListWithLargeCategory(largeId);
        return CommonResponse.success("해당 카테고리의 productIdList를 성공적으로 찾았습니다.",productIdListResponseList);
    }

    //중분류별 상품 조회
    @GetMapping("/medium/{mediumId}/")
    public CommonResponse<List<ProductIdListResponse>> productIdWithMediumCategoryList(@PathVariable("mediumId") Long mediumId){
        List<ProductIdListResponse> productIdListResponseList = productCategoryListService.getProductIdListWithMediumCategory(mediumId);
        return CommonResponse.success("해당 카테고리의 productIdList를 성공적으로 찾았습니다.",productIdListResponseList);
    }

    //소분류별 상품 조회
    @GetMapping("/small/{smallId}/")
    public CommonResponse<List<ProductIdListResponse>> productIdWithSmallCategoryList(@PathVariable("smallId") Long smallId){
        List<ProductIdListResponse> productIdListResponseList = productCategoryListService.getProductIdListWithSmallCategory(smallId);
        return CommonResponse.success("해당 카테고리의 productIdList를 성공적으로 찾았습니다.",productIdListResponseList);
    }

    //소소분류별 상품 조회
    @GetMapping("/tiny/{tinyId}/")
    public CommonResponse<List<ProductIdListResponse>> productIdWithTinyCategoryList(@PathVariable("tinyId") Long tinyId){
        List<ProductIdListResponse> productIdListResponseList = productCategoryListService.getProductIdListWithTinyCategory(tinyId);
        return CommonResponse.success("해당 카테고리의 productIdList를 성공적으로 찾았습니다.",productIdListResponseList);
    }
}
