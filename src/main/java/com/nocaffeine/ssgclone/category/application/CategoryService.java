package com.nocaffeine.ssgclone.category.application;

import com.nocaffeine.ssgclone.category.dto.response.ProductIdResponse;
import com.nocaffeine.ssgclone.category.dto.response.*;

import java.util.List;

public interface CategoryService {

    List<MediumCategoryResponse> findLargetoMedium(Long large_id);

    List<SmallCategoryResponse> findMediumtoSmall(Long medium_id);

    List<TinyCategoryResponse> findSmalltoTiny(Long small_id);

    List<ProductIdResponse> findProductIdToLarge(Long large_id);

    List<ProductListResponse> findProductListValueToLarge(Long product_id);
}
