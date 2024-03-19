package com.nocaffeine.ssgclone.category.application;

import com.nocaffeine.ssgclone.category.dto.request.MediumCategoryRequest;
import com.nocaffeine.ssgclone.category.dto.response.MediumCategoryDto;
import com.nocaffeine.ssgclone.category.dto.response.SmallCategoryDto;
import com.nocaffeine.ssgclone.category.dto.response.TinyCategoryDto;

import java.util.List;

public interface CategoryService {

    List<MediumCategoryDto> findLargetoMedium(Long large_id);

    List<SmallCategoryDto> findMediumtoSmall(Long medium_id);

    List<TinyCategoryDto> findSmalltoTiny(Long small_id);
}
