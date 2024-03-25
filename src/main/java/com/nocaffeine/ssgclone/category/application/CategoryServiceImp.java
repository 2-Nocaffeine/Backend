package com.nocaffeine.ssgclone.category.application;

import com.nocaffeine.ssgclone.category.domain.*;
import com.nocaffeine.ssgclone.category.dto.response.*;
import com.nocaffeine.ssgclone.category.infrastructure.*;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.No_TINY_CATEGORY;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImp implements CategoryService {

    private final LargeCategoryRepository largeCategoryRepository;
    private final MediumCategoryRepository mediumCategoryRepository;
    private final SmallCategoryRepository smallCategoryRepository;
    private final TinyCategoryRepository tinyCategoryRepository;


    @Override
    public List<LargeCategoryResponse> findLargeCategories() {
        List<LargeCategoryResponse> largeCategoryList = new ArrayList<>();

        for (LargeCategory largeCategory : largeCategoryRepository.findAll()){
            LargeCategoryResponse largeCategoryDto = LargeCategoryResponse.builder()
                    .largeCategoryId(largeCategory.getId())
                    .largeCategoryName(largeCategory.getName())
                    .build();
            largeCategoryList.add(largeCategoryDto);
        }
        return largeCategoryList;
    }

    @Override
    public List<MediumCategoryResponse> findMediumCategories(Long largeId) {

        List<MediumCategoryResponse> mediumCategoryDtoList = new ArrayList<>();

        for (MediumCategory mediumCategory : mediumCategoryRepository.findByLargeCategoryId(largeId)) {
            MediumCategoryResponse mediumCategoryDto = MediumCategoryResponse.builder()
                    .mediumCategoryId(mediumCategory.getId())
                    .mediumCategoryName(mediumCategory.getName())
                    .build();
            mediumCategoryDtoList.add(mediumCategoryDto);
        }
        return mediumCategoryDtoList;
    }

    @Override
    public List<SmallCategoryResponse> findSmallCategories(Long mediumId) {
        List<SmallCategoryResponse> smallCategoryDtoList = new ArrayList<>();

        for (SmallCategory smallCategory : smallCategoryRepository.findByMediumCategoryId(mediumId)) {
            SmallCategoryResponse smallCategoryDto = SmallCategoryResponse.builder()
                    .smallCategoryId(smallCategory.getId())
                    .smallCategoryName(smallCategory.getName())
                    .build();
            smallCategoryDtoList.add(smallCategoryDto);

        }

        return smallCategoryDtoList;
    }

    @Override
    public List<TinyCategoryResponse> findTinyCategories(Long smallId) {
        List<TinyCategoryResponse> tinyCategoryDtoList = new ArrayList<>();

        for (TinyCategory tinyCategory : tinyCategoryRepository.findBySmallCategoryId(smallId)) {
            TinyCategoryResponse tinyCategoryDto = TinyCategoryResponse.builder()
                    .tinyCategoryId(tinyCategory.getId())
                    .tinyCategoryName(tinyCategory.getName())
                    .build();
            tinyCategoryDtoList.add(tinyCategoryDto);
        }

        return tinyCategoryDtoList;
    }
}








