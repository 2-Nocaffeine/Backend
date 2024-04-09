package com.nocaffeine.ssgclone.category.application;

import com.nocaffeine.ssgclone.category.dto.response.*;

import java.util.List;

public interface CategoryService {

    List<LargeCategoryResponseDto> findLargeCategories();
    List<MediumCategoryResponseDto> findMediumCategories(Long largeId);

    List<SmallCategoryResponseDto> findSmallCategories(Long mediumId);

    List<TinyCategoryResponseDto> findTinyCategories(Long smallId);


}
