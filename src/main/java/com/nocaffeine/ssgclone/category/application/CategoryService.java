package com.nocaffeine.ssgclone.category.application;

import com.nocaffeine.ssgclone.category.dto.response.*;

import java.util.List;

public interface CategoryService {

    List<LargeCategoryResponse> findLargeCategories();
    List<MediumCategoryResponse> findMediumCategories(Long largeId);

    List<SmallCategoryResponse> findSmallCategories(Long mediumId);

    List<TinyCategoryResponse> findTinyCategories(Long smallId);


}
