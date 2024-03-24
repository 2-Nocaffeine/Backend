package com.nocaffeine.ssgclone.category.application;

import com.nocaffeine.ssgclone.category.dto.response.ProductListIdResponse;

import java.util.List;

public interface ProductListWithCategoryService {
    List<ProductListIdResponse> getProductListWithCategory(long largeId);

}
