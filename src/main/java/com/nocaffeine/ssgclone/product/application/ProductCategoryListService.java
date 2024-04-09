package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.product.dto.response.ProductIdListResponseDto;

import java.util.List;

public interface ProductCategoryListService {
    List<ProductIdListResponseDto> getProductIdListWithLargeCategory(Long largeId);

    List<ProductIdListResponseDto> getProductIdListWithMediumCategory(Long mediumId);

    List<ProductIdListResponseDto> getProductIdListWithSmallCategory(Long smallId);

    List<ProductIdListResponseDto> getProductIdListWithTinyCategory(Long tinyId);
}
