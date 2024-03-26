package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.product.dto.response.ProductIdListResponse;

import java.util.List;

public interface ProductCategoryListService {
    List<ProductIdListResponse> getProductIdListWithLargeCategory(Long largeId);

    List<ProductIdListResponse> getProductIdListWithMediumCategory(Long mediumId);

    List<ProductIdListResponse> getProductIdListWithSmallCategory(Long smallId);

    List<ProductIdListResponse> getProductIdListWithTinyCategory(Long tinyId);
}
