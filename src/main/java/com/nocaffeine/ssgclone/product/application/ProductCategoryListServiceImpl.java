package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.category.domain.ProductCategoryList;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.product.dto.response.ProductIdListResponseDto;
import com.nocaffeine.ssgclone.product.infrastructure.ProductCategoryListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_PRODUCT;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductCategoryListServiceImpl implements ProductCategoryListService {

    private final ProductCategoryListRepository productCategoryListRepository;

    @Override
    public List<ProductIdListResponseDto> getProductIdListWithLargeCategory(Long largeId) {
        List<ProductIdListResponseDto> productIdListResponseListDto = new ArrayList<>();

        for (ProductCategoryList productCategoryList : productCategoryListRepository.findByLargeCategoryId(largeId)){
            ProductIdListResponseDto productIdListResponseDto = ProductIdListResponseDto.builder()
                    .id(productCategoryList.getId())
                    .productId(productCategoryList.getProduct().getId())
                    .build();
            productIdListResponseListDto.add(productIdListResponseDto);
        }
        return productIdListResponseListDto;
    }

    @Override
    public List<ProductIdListResponseDto> getProductIdListWithMediumCategory(Long mediumId) {
        List<ProductIdListResponseDto> productIdListResponseListDto = new ArrayList<>();

        for (ProductCategoryList productCategoryList : productCategoryListRepository.findByMediumCategoryId(mediumId)){
            ProductIdListResponseDto productIdListResponseDto = ProductIdListResponseDto.builder()
                    .id(productCategoryList.getId())
                    .productId(productCategoryList.getProduct().getId())
                    .build();
            productIdListResponseListDto.add(productIdListResponseDto);
        }
        return productIdListResponseListDto;
    }

    @Override
    public List<ProductIdListResponseDto> getProductIdListWithSmallCategory(Long smallId) {
        List<ProductIdListResponseDto> productIdListResponseListDto = new ArrayList<>();

        for (ProductCategoryList productCategoryList : productCategoryListRepository.findBySmallCategoryId(smallId)){
            ProductIdListResponseDto productIdListResponseDto = ProductIdListResponseDto.builder()
                    .id(productCategoryList.getId())
                    .productId(productCategoryList.getProduct().getId())
                    .build();
            productIdListResponseListDto.add(productIdListResponseDto);
        }
        return productIdListResponseListDto;
    }

    @Override
    public List<ProductIdListResponseDto> getProductIdListWithTinyCategory(Long tinyId) {

        List<ProductCategoryList> productCategoryLists = productCategoryListRepository.findByTinyCategory(tinyId);
        if (productCategoryLists.isEmpty()){
                throw new BaseException(NO_PRODUCT);
        }

        List<ProductIdListResponseDto> productIdList = new ArrayList<>();

        for (ProductCategoryList productCategoryList : productCategoryLists){
            ProductIdListResponseDto productIdListResponseDto = ProductIdListResponseDto.builder()
                    .id(productCategoryList.getId())
                    .productId(productCategoryList.getProduct().getId())
                    .build();
            productIdList.add(productIdListResponseDto);
        }
        return productIdList;
    }




}

