package com.nocaffeine.ssgclone.category.application;

import com.nocaffeine.ssgclone.category.domain.*;
import com.nocaffeine.ssgclone.category.dto.response.*;
import com.nocaffeine.ssgclone.category.infrastructure.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImp implements CategoryService {

    private final LargeCategoryRepository largeCategoryRepository;
    private final MediumCategoryRepository mediumCategoryRepository;
    private final SmallCategoryRepository smallCategoryRepository;
    private final TinyCategoryRepository tinyCategoryRepository;


    @Override
    public List<LargeCategoryResponseDto> findLargeCategories() {
        List<LargeCategoryResponseDto> largeCategoryList = new ArrayList<>();

        for (LargeCategory largeCategory : largeCategoryRepository.findAll()){
            LargeCategoryResponseDto largeCategoryDto = LargeCategoryResponseDto.builder()
                    .largeCategoryId(largeCategory.getId())
                    .largeCategoryName(largeCategory.getName())
                    .imageUrl(largeCategory.getImageUrl())
                    .build();
            largeCategoryList.add(largeCategoryDto);
        }
        return largeCategoryList;
    }

    @Override
    public List<MediumCategoryResponseDto> findMediumCategories(Long largeId) {

        List<MediumCategoryResponseDto> mediumCategoryDtoList = new ArrayList<>();

        for (MediumCategory mediumCategory : mediumCategoryRepository.findByLargeCategoryId(largeId)) {
            MediumCategoryResponseDto mediumCategoryDto = MediumCategoryResponseDto.builder()
                    .mediumCategoryId(mediumCategory.getId())
                    .mediumCategoryName(mediumCategory.getName())
                    .build();
            mediumCategoryDtoList.add(mediumCategoryDto);
        }
        return mediumCategoryDtoList;
    }

    @Override
    public List<SmallCategoryResponseDto> findSmallCategories(Long mediumId) {
        List<SmallCategoryResponseDto> smallCategoryDtoList = new ArrayList<>();

        for (SmallCategory smallCategory : smallCategoryRepository.findByMediumCategoryId(mediumId)) {
            SmallCategoryResponseDto smallCategoryDto = SmallCategoryResponseDto.builder()
                    .smallCategoryId(smallCategory.getId())
                    .smallCategoryName(smallCategory.getName())
                    .build();
            smallCategoryDtoList.add(smallCategoryDto);

        }

        return smallCategoryDtoList;
    }

    @Override
    public List<TinyCategoryResponseDto> findTinyCategories(Long smallId) {
        List<TinyCategoryResponseDto> tinyCategoryDtoList = new ArrayList<>();

        for (TinyCategory tinyCategory : tinyCategoryRepository.findBySmallCategoryId(smallId)) {
            TinyCategoryResponseDto tinyCategoryDto = TinyCategoryResponseDto.builder()
                    .tinyCategoryId(tinyCategory.getId())
                    .tinyCategoryName(tinyCategory.getName())
                    .build();
            tinyCategoryDtoList.add(tinyCategoryDto);
        }

        return tinyCategoryDtoList;
    }
}








