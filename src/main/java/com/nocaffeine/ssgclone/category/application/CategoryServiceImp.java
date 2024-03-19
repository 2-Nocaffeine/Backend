package com.nocaffeine.ssgclone.category.application;

import com.nocaffeine.ssgclone.category.domain.MediumCategory;
import com.nocaffeine.ssgclone.category.domain.SmallCategory;
import com.nocaffeine.ssgclone.category.domain.TinyCategory;
import com.nocaffeine.ssgclone.category.dto.request.MediumCategoryRequest;
import com.nocaffeine.ssgclone.category.dto.response.MediumCategoryDto;
import com.nocaffeine.ssgclone.category.dto.response.SmallCategoryDto;
import com.nocaffeine.ssgclone.category.dto.response.TinyCategoryDto;
import com.nocaffeine.ssgclone.category.infrastructure.LargeCategoryRepository;
import com.nocaffeine.ssgclone.category.infrastructure.MediumCategoryRepository;
import com.nocaffeine.ssgclone.category.infrastructure.SmallCategoryRepository;
import com.nocaffeine.ssgclone.category.infrastructure.TinyCategoryRepository;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.No_Tiny_Category;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImp implements CategoryService{

    private final MediumCategoryRepository mediumCategoryRepository;
    private final SmallCategoryRepository smallCategoryRepository;
    private final TinyCategoryRepository tinyCategoryRepository;

    @Override
    public List<MediumCategoryDto> findLargetoMedium(Long large_id) {

        List<MediumCategoryDto> mediumCategoryDtoList = new ArrayList<>();

        for (MediumCategory mediumCategory : mediumCategoryRepository.findByLargeCategory_Id(large_id)) {
            MediumCategoryDto mediumCategoryDto = new MediumCategoryDto();
            mediumCategoryDto.setId(mediumCategory.getId());
            mediumCategoryDto.setName(mediumCategory.getName());
            mediumCategoryDtoList.add(mediumCategoryDto);
        }

        return mediumCategoryDtoList;
    }

    @Override
    public List<SmallCategoryDto> findMediumtoSmall(Long medium_id) {
        List<SmallCategoryDto> smallCategoryDtoList = new ArrayList<>();

        for (SmallCategory smallCategory : smallCategoryRepository.findByMediumCategory_Id(medium_id)) {
            SmallCategoryDto smallCategoryDto = new SmallCategoryDto();
            smallCategoryDto.setId(smallCategory.getId());
            smallCategoryDto.setName(smallCategory.getName());
            smallCategoryDtoList.add(smallCategoryDto);
        }

        return smallCategoryDtoList;
    }

    @Override
    public List<TinyCategoryDto> findSmalltoTiny(Long small_id) {
        List<TinyCategoryDto> TinyCategoryDtoList = new ArrayList<>();

        for (TinyCategory tinyCategory : tinyCategoryRepository.findBySmallCategory_Id(small_id)) {
            if (tinyCategory == null) {
                throw new BaseException(No_Tiny_Category);
            }
            TinyCategoryDto tinyCategoryDto = new TinyCategoryDto();
            tinyCategoryDto.setId(tinyCategory.getId());
            tinyCategoryDto.setName(tinyCategory.getName());
            TinyCategoryDtoList.add(tinyCategoryDto);
        }

        return TinyCategoryDtoList;
    }

}
