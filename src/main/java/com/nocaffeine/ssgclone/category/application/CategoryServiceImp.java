package com.nocaffeine.ssgclone.category.application;

import com.nocaffeine.ssgclone.category.domain.LargeCategory;
import com.nocaffeine.ssgclone.category.domain.MediumCategory;
import com.nocaffeine.ssgclone.category.domain.SmallCategory;
import com.nocaffeine.ssgclone.category.domain.TinyCategory;
import com.nocaffeine.ssgclone.category.dto.LargeCategoryRequest;
import com.nocaffeine.ssgclone.category.dto.MediumCategoryRequest;
import com.nocaffeine.ssgclone.category.dto.SmallCategoryRequest;
import com.nocaffeine.ssgclone.category.infrastructure.LargeCategoryRepository;
import com.nocaffeine.ssgclone.category.infrastructure.MediumCategoryRepository;
import com.nocaffeine.ssgclone.category.infrastructure.SmallCategoryRepository;
import com.nocaffeine.ssgclone.category.infrastructure.TinyCategoryRepository;
import com.nocaffeine.ssgclone.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImp implements CategoryService{

    private final LargeCategoryRepository largeCategoryRepository;
    private final MediumCategoryRepository mediumCategoryRepository;
    private final SmallCategoryRepository smallCategoryRepository;
    private final TinyCategoryRepository tinyCategoryRepository;

    @Override
    public ResponseDto<List<MediumCategory>> findLargetoMedium(LargeCategoryRequest largeCategoryRequest) {
        LargeCategory largeCategory = largeCategoryRepository.findByName(largeCategoryRequest.getName());
        List<MediumCategory> mediumCategories = mediumCategoryRepository.findByLargeCategory_Id(largeCategory.getId());
        return new ResponseDto<>(true, "Success", mediumCategories,null);
    }

    @Override
    public ResponseDto<List<SmallCategory>> findMediumtoSmall(MediumCategoryRequest mediumCategoryRequest) {
        MediumCategory mediumCategory = mediumCategoryRepository.findByName(mediumCategoryRequest.getName());
        List<SmallCategory> smallCategories = smallCategoryRepository.findByMediumCategory_Id(mediumCategory.getId());
        return new ResponseDto<>(true, "Success", smallCategories,null);
    }

    @Override
    public ResponseDto<List<TinyCategory>> findSmalltoTiny(SmallCategoryRequest smallCategoryRequest) {
        SmallCategory smallCategory = smallCategoryRepository.findByName(smallCategoryRequest.getName());
        List<TinyCategory> tinyCategories = tinyCategoryRepository.findBySmallCategory_Id(smallCategory.getId());
        return new ResponseDto<>(true, "Success", tinyCategories,null);
    }


}
