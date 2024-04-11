package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.product.dto.response.product.CategoryProductPageListResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.product.ProductIdListResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductCategoryListService {

    // 대 카테고리 아이디로 상품 아이디 리스트 조회
    List<ProductIdListResponseDto> getProductIdListWithLargeCategory(Long largeId);

    CategoryProductPageListResponseDto getProductIdListWithLargeCategoryPaged(Long largeId, Pageable page);

    // 중 카테고리 아이디로 상품 아이디 리스트 조회
    List<ProductIdListResponseDto> getProductIdListWithMediumCategory(Long mediumId);

    CategoryProductPageListResponseDto getProductIdListWithMediumCategoryPaged(Long mediumId, Pageable page);

    // 소 카테고리 아이디로 상품 아이디 리스트 조회
    List<ProductIdListResponseDto> getProductIdListWithSmallCategory(Long smallId);

    CategoryProductPageListResponseDto getProductIdListWithSmallCategoryPaged(Long smallId, Pageable page);

    // 소소 카테고리 아이디로 상품 아이디 리스트 조회
    List<ProductIdListResponseDto> getProductIdListWithTinyCategory(Long tinyId);

    CategoryProductPageListResponseDto getProductIdListWithTinyCategoryPaged(Long tinyId, Pageable page);
}
