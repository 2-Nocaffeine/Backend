package com.nocaffeine.ssgclone.category.application;

import com.nocaffeine.ssgclone.category.domain.MediumCategory;
import com.nocaffeine.ssgclone.category.domain.ProductList;
import com.nocaffeine.ssgclone.category.domain.SmallCategory;
import com.nocaffeine.ssgclone.category.domain.TinyCategory;
import com.nocaffeine.ssgclone.category.dto.response.MediumCategoryResponse;
import com.nocaffeine.ssgclone.category.dto.response.ProductListResponse;
import com.nocaffeine.ssgclone.category.dto.response.SmallCategoryResponse;
import com.nocaffeine.ssgclone.category.dto.response.TinyCategoryResponse;
import com.nocaffeine.ssgclone.category.infrastructure.MediumCategoryRepository;
import com.nocaffeine.ssgclone.category.infrastructure.ProductListRepository;
import com.nocaffeine.ssgclone.category.infrastructure.SmallCategoryRepository;
import com.nocaffeine.ssgclone.category.infrastructure.TinyCategoryRepository;
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
public class CategoryServiceImp implements CategoryService{

    private final MediumCategoryRepository mediumCategoryRepository;
    private final SmallCategoryRepository smallCategoryRepository;
    private final TinyCategoryRepository tinyCategoryRepository;
    private final ProductListRepository productListRepository;

    @Override
    public List<MediumCategoryResponse> findLargetoMedium(Long large_id) {

        List<MediumCategoryResponse> mediumCategoryDtoList = new ArrayList<>();

        for (MediumCategory mediumCategory : mediumCategoryRepository.findByLargeCategory_Id(large_id)) {
            MediumCategoryResponse mediumCategoryDto = MediumCategoryResponse.builder()
                    .id(mediumCategory.getId())
                    .name(mediumCategory.getName())
                    .build();
            mediumCategoryDtoList.add(mediumCategoryDto);
        }

    }

    @Override
    public List<SmallCategoryResponse> findMediumtoSmall(Long medium_id) {
        List<SmallCategoryResponse> smallCategoryDtoList = new ArrayList<>();

        for (SmallCategory smallCategory : smallCategoryRepository.findByMediumCategory_Id(medium_id)) {
            SmallCategoryResponse smallCategoryDto = new SmallCategoryResponse();
            smallCategoryDto.setId(smallCategory.getId());
            smallCategoryDto.setName(smallCategory.getName());
            smallCategoryDtoList.add(smallCategoryDto);
        }

        return smallCategoryDtoList;
    }

    @Override
    public List<TinyCategoryResponse> findSmalltoTiny(Long small_id) {
        List<TinyCategoryResponse> TinyCategoryDtoList = new ArrayList<>();

        for (TinyCategory tinyCategory : tinyCategoryRepository.findBySmallCategory_Id(small_id)) {
            if (tinyCategory == null) {
                throw new BaseException(No_TINY_CATEGORY);
            }
            TinyCategoryResponse tinyCategoryDto = new TinyCategoryResponse();
            tinyCategoryDto.setId(tinyCategory.getId());
            tinyCategoryDto.setName(tinyCategory.getName());
            TinyCategoryDtoList.add(tinyCategoryDto);
        }

        return TinyCategoryDtoList;
    }

    @Override
    public List<ProductList> findProductcategory(Long largeId) {
        List<ProductList> productcategorylist = new ArrayList<>();

        for (ProductList productList : productListRepository.findByLargeCategory_Id(largeId){
            ProductListResponse productcategory = new ProductListResponse();
            productcategorylist.add(productcategory.setProduct(productList.getProduct()));
        }
    }

}
