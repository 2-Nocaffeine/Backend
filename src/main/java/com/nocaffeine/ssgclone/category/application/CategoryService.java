package com.nocaffeine.ssgclone.category.application;

import com.nocaffeine.ssgclone.category.domain.ProductList;
import com.nocaffeine.ssgclone.category.dto.response.MediumCategoryResponse;
import com.nocaffeine.ssgclone.category.dto.response.ProductListResponse;
import com.nocaffeine.ssgclone.category.dto.response.SmallCategoryResponse;
import com.nocaffeine.ssgclone.category.dto.response.TinyCategoryResponse;

import java.util.List;

public interface CategoryService {

    List<MediumCategoryResponse> findLargetoMedium(Long large_id);

    List<SmallCategoryResponse> findMediumtoSmall(Long medium_id);

    List<TinyCategoryResponse> findSmalltoTiny(Long small_id);

//    List<ProductListResponse> findProductToLarge(Long largeId);
}
