package com.nocaffeine.ssgclone.product.presentation;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.product.application.ProductCategoryListService;
import com.nocaffeine.ssgclone.product.dto.response.product.CategoryProductPageListResponseDto;
import com.nocaffeine.ssgclone.product.vo.response.product.CategoryProductPageListResponseVo;
import com.nocaffeine.ssgclone.product.vo.response.product.ProductIdListResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    @Operation(summary = "대카테고리 상품 id 조회", description = "대카테고리 상품 id 조회", tags = {"Category Product List"})
    @GetMapping("/large-category/{largeId}")
    public CommonResponse<List<ProductIdListResponseVo>> productIdWithLargeCategoryList(@PathVariable("largeId") Long largeId){

        return CommonResponse.success("해당 카테고리의 productIdList 를 성공적으로 찾았습니다.",
                ProductIdListResponseVo.convertToVo(productCategoryListService.getProductIdListWithLargeCategory(largeId)));
    }

    //대분류별 상품 페이징 조회
    @Operation(summary = "대카테고리 상품 id 페이징 조회", description = "대카테고리 상품 id 페이징 조회", tags = {"Category Product List"})
    @GetMapping("/large-category-paged/{largeId}")
    public CommonResponse<CategoryProductPageListResponseVo> productIdWithLargeCategoryListPaged(
            @PathVariable("largeId") Long largeId,
            @PageableDefault(size = 20, sort = "id") Pageable page){

        CategoryProductPageListResponseDto categoryProductPageListResponseDto = productCategoryListService.getProductIdListWithLargeCategoryPaged(largeId, page);

        return CommonResponse.success("해당 카테고리의 productIdList 를 성공적으로 찾았습니다.",
                CategoryProductPageListResponseVo.fromCategoryProductPageListResponseVo(categoryProductPageListResponseDto));
    }

    //중분류별 상품 조회

    @Operation(summary = "중카테고리 상품 id 조회", description = "중카테고리 상품 id 조회", tags = {"Category Product List"})
    @GetMapping("/medium-category/{mediumId}")
    public CommonResponse<List<ProductIdListResponseVo>> productIdWithMediumCategoryList(@PathVariable("mediumId") Long mediumId){

        return CommonResponse.success("해당 카테고리의 productIdList 를 성공적으로 찾았습니다.",
                ProductIdListResponseVo.convertToVo(productCategoryListService.getProductIdListWithMediumCategory(mediumId)));
    }

    //중분류별 상품 페이징 조회
    @Operation(summary = "중카테고리 상품 id 페이징 조회", description = "중카테고리 상품 id 페이징 조회", tags = {"Category Product List"})
    @GetMapping("/medium-category-paged/{mediumId}")
    public CommonResponse<CategoryProductPageListResponseVo> productIdWithMediumCategoryListPaged(
            @PathVariable("mediumId") Long mediumId,
            @PageableDefault(size = 20, sort = "id") Pageable page){

        CategoryProductPageListResponseDto categoryProductPageListResponseDto = productCategoryListService.getProductIdListWithMediumCategoryPaged(mediumId, page);

        return CommonResponse.success("해당 카테고리의 productIdList 를 성공적으로 찾았습니다.",
                CategoryProductPageListResponseVo.fromCategoryProductPageListResponseVo(categoryProductPageListResponseDto));
    }

    //소분류별 상품 조회
    @Operation(summary = "소카테고리 상품 id 조회", description = "소카테고리 상품 id 조회", tags = {"Category Product List"})
    @GetMapping("/small-category/{smallId}")
    public CommonResponse<List<ProductIdListResponseVo>> productIdWithSmallCategoryList(@PathVariable("smallId") Long smallId){

        return CommonResponse.success("해당 카테고리의 productIdList 를 성공적으로 찾았습니다.",
                ProductIdListResponseVo.convertToVo(productCategoryListService.getProductIdListWithSmallCategory(smallId)));
    }

    //소분류별 상품 페이징 조회
    @Operation(summary = "소카테고리 상품 id 페이징 조회", description = "소카테고리 상품 id 페이징 조회", tags = {"Category Product List"})
    @GetMapping("/small-category-paged/{smallId}")
    public CommonResponse<CategoryProductPageListResponseVo> productIdWithSmallCategoryListPaged(
            @PathVariable("smallId") Long smallId,
            @PageableDefault(size = 20, sort = "id") Pageable page){

        CategoryProductPageListResponseDto categoryProductPageListResponseDto = productCategoryListService.getProductIdListWithSmallCategoryPaged(smallId, page);

        return CommonResponse.success("해당 카테고리의 productIdList 를 성공적으로 찾았습니다.",
                CategoryProductPageListResponseVo.fromCategoryProductPageListResponseVo(categoryProductPageListResponseDto));
    }

    //소소분류별 상품 조회
    @Operation(summary = "소소카테고리 상품 id 조회", description = "소소카테고리 상품 id 조회", tags = {"Category Product List"})
    @GetMapping("/tiny-category/{tinyId}")
    public CommonResponse<List<ProductIdListResponseVo>> productIdWithTinyCategoryList(@PathVariable("tinyId") Long tinyId){

        return CommonResponse.success("해당 카테고리의 productIdList 를 성공적으로 찾았습니다.",
                ProductIdListResponseVo.convertToVo(productCategoryListService.getProductIdListWithTinyCategory(tinyId)));
    }

    //소소분류별 상품 페이징 조회
    @Operation(summary = "소소카테고리 상품 id 페이징 조회", description = "소소카테고리 상품 id 페이징 조회", tags = {"Category Product List"})
    @GetMapping("/tiny-category-paged/{tinyId}")
    public CommonResponse<CategoryProductPageListResponseVo> productIdWithTinyCategoryListPaged(
            @PathVariable("tinyId") Long tinyId,
            @PageableDefault(size = 20, sort = "id") Pageable page){

        CategoryProductPageListResponseDto categoryProductPageListResponseDto = productCategoryListService.getProductIdListWithTinyCategoryPaged(tinyId, page);

        return CommonResponse.success("해당 카테고리의 productIdList 를 성공적으로 찾았습니다.",
                CategoryProductPageListResponseVo.fromCategoryProductPageListResponseVo(categoryProductPageListResponseDto));
    }
}
