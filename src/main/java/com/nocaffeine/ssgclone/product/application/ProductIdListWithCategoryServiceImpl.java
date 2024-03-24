package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.category.domain.ProductCategoryList;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.product.dto.response.ProductIdListResponse;
import com.nocaffeine.ssgclone.product.infrastructure.ProductIdListWithCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_PRODUCT;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductIdListWithCategoryServiceImpl implements ProductIdListWithCategoryService {

    private final ProductIdListWithCategoryRepository ProductIdListWithCategoryRepository;

    @Override
    public List<ProductIdListResponse> getProductIdListWithLargeCategory(Long largeId) {
        List<ProductIdListResponse> productIdListResponseList = new ArrayList<>();
        int sequenceId = 1;

        for (ProductCategoryList productCategoryList : ProductIdListWithCategoryRepository.findByLargeCategoryId(largeId)){
            ProductIdListResponse productIdListResponse = ProductIdListResponse.builder()
                    .id(sequenceId++)
                    .ProductId(productCategoryList.getProduct().getId())
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
        int sequenceId = 1;

        for (ProductCategoryList productCategoryList : ProductIdListWithCategoryRepository.findByMediumCategoryId(mediumId)){
            ProductIdListResponse productIdListResponse = ProductIdListResponse.builder()
                    .id(sequenceId++)
                    .ProductId(productCategoryList.getProduct().getId())
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
        int sequenceId = 1;

        for (ProductCategoryList productCategoryList : ProductIdListWithCategoryRepository.findBySmallCategoryId(smallId)){
            ProductIdListResponse productIdListResponse = ProductIdListResponse.builder()
                    .id(sequenceId++)
                    .ProductId(productCategoryList.getProduct().getId())
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

        List<ProductCategoryList> productCategoryLists = ProductIdListWithCategoryRepository.findByTinyCategory(tinyId);
        if (productCategoryLists.isEmpty()){
                throw new BaseException(NO_PRODUCT);
        }

        List<ProductIdListResponse> productIdList = new ArrayList<>();
        int sequenceId = 1;
        for (ProductCategoryList productCategoryList : productCategoryLists){
            ProductIdListResponse productIdListResponse = ProductIdListResponse.builder()
                    .id(sequenceId++)
                    .ProductId(productCategoryList.getProduct().getId())
                    .build();
            productIdList.add(productIdListResponse);
        }
        return productIdList;
    }




}

