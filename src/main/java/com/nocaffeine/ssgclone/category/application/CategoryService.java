package com.nocaffeine.ssgclone.category.application;

import com.nocaffeine.ssgclone.category.dto.response.*;

import java.util.List;

public interface CategoryService {

    List<LargeCategoryResponseDto> findLargeCategories();
    List<CategoryResponseDto> findMediumCategories(Long largeId);

    List<CategoryResponseDto> findSmallCategories(Long mediumId);

    List<CategoryResponseDto> findTinyCategories(Long smallId);


}
