package com.nocaffeine.ssgclone.category.application;

import com.nocaffeine.ssgclone.category.domain.MediumCategory;
import com.nocaffeine.ssgclone.category.domain.SmallCategory;
import com.nocaffeine.ssgclone.category.domain.TinyCategory;
import com.nocaffeine.ssgclone.category.dto.LargeCategoryRequest;
import com.nocaffeine.ssgclone.category.dto.MediumCategoryRequest;
import com.nocaffeine.ssgclone.category.dto.SmallCategoryRequest;
import com.nocaffeine.ssgclone.common.ResponseDto;

import java.util.List;

public interface CategoryService {

    ResponseDto<List<MediumCategory>> findLargetoMedium(LargeCategoryRequest largeCategoryRequest);

    ResponseDto<List<SmallCategory>> findMediumtoSmall(MediumCategoryRequest mediumCategoryRequest);


    ResponseDto<List<TinyCategory>> findSmalltoTiny(SmallCategoryRequest smallCategoryRequest);
}
