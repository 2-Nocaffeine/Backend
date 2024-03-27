package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.category.domain.ProductCategoryList;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.product.dto.response.ProductIdListResponse;
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
    public List<ProductIdListResponse> getProductIdListWithLargeCategory(Long largeId) {
        List<ProductIdListResponse> productIdListResponseList = new ArrayList<>();

        for (ProductCategoryList productCategoryList : productCategoryListRepository.findByLargeCategoryId(largeId)){
            ProductIdListResponse productIdListResponse = ProductIdListResponse.builder()
                    .id(productCategoryList.getId())
                    .productId(productCategoryList.getProduct().getId())
                    .build();
            productIdListResponseList.add(productIdListResponse);
        }
        if (productIdListResponseList.isEmpty()) {
            throw new BaseException(NO_PRODUCT);
        }
        return productIdListResponseList;
    }

    @Override
    public List<ProductIdListResponse> getProductIdListWithMediumCategory(Long mediumId) {
        List<ProductIdListResponse> productIdListResponseList = new ArrayList<>();

        for (ProductCategoryList productCategoryList : productCategoryListRepository.findByMediumCategoryId(mediumId)){
            ProductIdListResponse productIdListResponse = ProductIdListResponse.builder()
                    .id(productCategoryList.getId())
                    .productId(productCategoryList.getProduct().getId())
                    .build();
            productIdListResponseList.add(productIdListResponse);
        }
        if (productIdListResponseList.isEmpty()){
            throw new BaseException(NO_PRODUCT);
        }
        return productIdListResponseList;
    }

    @Override
    public List<ProductIdListResponse> getProductIdListWithSmallCategory(Long smallId) {
        List<ProductIdListResponse> productIdListResponseList = new ArrayList<>();

        for (ProductCategoryList productCategoryList : productCategoryListRepository.findBySmallCategoryId(smallId)){
            ProductIdListResponse productIdListResponse = ProductIdListResponse.builder()
                    .id(productCategoryList.getId())
                    .productId(productCategoryList.getProduct().getId())
                    .build();
            productIdListResponseList.add(productIdListResponse);
        }
        if (productIdListResponseList.isEmpty()){
            throw new BaseException(NO_PRODUCT);
        }
        return productIdListResponseList;
    }

    @Override
    public List<ProductIdListResponse> getProductIdListWithTinyCategory(Long tinyId) {

        List<ProductCategoryList> productCategoryLists = productCategoryListRepository.findByTinyCategory(tinyId);
        if (productCategoryLists.isEmpty()){
                throw new BaseException(NO_PRODUCT);
        }

        List<ProductIdListResponse> productIdList = new ArrayList<>();

        for (ProductCategoryList productCategoryList : productCategoryLists){
            ProductIdListResponse productIdListResponse = ProductIdListResponse.builder()
                    .id(productCategoryList.getId())
                    .productId(productCategoryList.getProduct().getId())
                    .build();
            productIdList.add(productIdListResponse);
        }
        return productIdList;
    }




}

