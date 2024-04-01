package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.product.application.ProductCategoryListService;
import com.nocaffeine.ssgclone.product.dto.response.ProductIdListResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductCategoryListController {

    private final ProductCategoryListService productCategoryListService;

    //대분류별 상품 조회
    @Operation(summary = "대카테고리 상품 id 조회", description = "대카테고리 상품 id 조회", tags = {"Large Category Product List"})
    @GetMapping("/largeCategory/{largeId}")
    public CommonResponse<List<ProductIdListResponse>> productIdWithLargeCategoryList(@PathVariable("largeId") Long largeId){

        return CommonResponse.success("해당 카테고리의 productIdList 를 성공적으로 찾았습니다.",
                productCategoryListService.getProductIdListWithLargeCategory(largeId));
    }

    //중분류별 상품 조회

    @Operation(summary = "중카테고리 상품 id 조회", description = "중카테고리 상품 id 조회", tags = {"Medium Category Product List"})
    @GetMapping("/mediumCategory/{mediumId}")
    public CommonResponse<List<ProductIdListResponse>> productIdWithMediumCategoryList(@PathVariable("mediumId") Long mediumId){

        return CommonResponse.success("해당 카테고리의 productIdList 를 성공적으로 찾았습니다.",
                productCategoryListService.getProductIdListWithMediumCategory(mediumId));
    }

    //소분류별 상품 조회
    @Operation(summary = "소카테고리 상품 id 조회", description = "소카테고리 상품 id 조회", tags = {"Small Category Product List"})
    @GetMapping("/smallCategory/{smallId}")
    public CommonResponse<List<ProductIdListResponse>> productIdWithSmallCategoryList(@PathVariable("smallId") Long smallId){

        return CommonResponse.success("해당 카테고리의 productIdList 를 성공적으로 찾았습니다.",
                productCategoryListService.getProductIdListWithSmallCategory(smallId));
    }

    //소소분류별 상품 조회
    @Operation(summary = "소소카테고리 상품 id 조회", description = "소소카테고리 상품 id 조회", tags = {"Tiny Category Product List"})
    @GetMapping("/tinyCategory/{tinyId}")
    public CommonResponse<List<ProductIdListResponse>> productIdWithTinyCategoryList(@PathVariable("tinyId") Long tinyId){

        return CommonResponse.success("해당 카테고리의 productIdList 를 성공적으로 찾았습니다.",
                productCategoryListService.getProductIdListWithTinyCategory(tinyId));
    }
}
