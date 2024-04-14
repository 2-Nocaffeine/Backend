package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.category.domain.ProductCategoryList;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import com.nocaffeine.ssgclone.product.domain.Product;
import com.nocaffeine.ssgclone.product.dto.response.product.CategoryProductPageListResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.product.ProductIdListResponseDto;
import com.nocaffeine.ssgclone.product.infrastructure.ProductCategoryListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public CategoryProductPageListResponseDto getProductIdListWithLargeCategoryPaged(Long largeId, Pageable page) {

        Page<ProductCategoryList> productCategoryLists = productCategoryListRepository.findByLargeCategoryId(largeId, page);

        List<ProductIdListResponseDto> responses = new ArrayList<>();

        for (ProductCategoryList productCategoryList : productCategoryLists) {

            responses.add(ProductIdListResponseDto.fromProductIdListResponseDto(productCategoryList));
        }

        return CategoryProductPageListResponseDto.fromCategoryProductPageListResponseDto(productCategoryLists.hasNext(), productCategoryLists.isLast(), responses);
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
    public CategoryProductPageListResponseDto getProductIdListWithMediumCategoryPaged(Long mediumId, Pageable page) {

        Page<ProductCategoryList> productCategoryLists = productCategoryListRepository.findByMediumCategoryId(mediumId, page);

        List<ProductIdListResponseDto> responses = new ArrayList<>();

        for (ProductCategoryList productCategoryList : productCategoryLists) {

            responses.add(ProductIdListResponseDto.fromProductIdListResponseDto(productCategoryList));
        }

        return CategoryProductPageListResponseDto.fromCategoryProductPageListResponseDto(productCategoryLists.hasNext(), productCategoryLists.isLast(), responses);
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
    public CategoryProductPageListResponseDto getProductIdListWithSmallCategoryPaged(Long smallId, Pageable page) {

        Page<ProductCategoryList> productCategoryLists = productCategoryListRepository.findBySmallCategoryId(smallId, page);

        List<ProductIdListResponseDto> responses = new ArrayList<>();

        for (ProductCategoryList productCategoryList : productCategoryLists) {

            responses.add(ProductIdListResponseDto.fromProductIdListResponseDto(productCategoryList));
        }

        return CategoryProductPageListResponseDto.fromCategoryProductPageListResponseDto(productCategoryLists.hasNext(), productCategoryLists.isLast(), responses);
    }

    @Override
    public List<ProductIdListResponseDto> getProductIdListWithTinyCategory(Long tinyId) {

        List<ProductCategoryList> productCategoryLists = productCategoryListRepository.findByTinyCategory(tinyId);
//        if (productCategoryLists.isEmpty()){
//                throw new BaseException(NO_PRODUCT);
//        }

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

    @Override
    public CategoryProductPageListResponseDto getProductIdListWithTinyCategoryPaged(Long tinyId, Pageable page) {

        Page<ProductCategoryList> productCategoryLists = productCategoryListRepository.findByTinyCategory(tinyId, page);

//        if (productCategoryLists.isEmpty()){ // 상품 카테고리 리스트가 비어있을 때
//            throw new BaseException(NO_PRODUCT);
//        }

        List<ProductIdListResponseDto> responses = new ArrayList<>();

        for (ProductCategoryList productCategoryList : productCategoryLists) {

            responses.add(ProductIdListResponseDto.fromProductIdListResponseDto(productCategoryList));
        }

        return CategoryProductPageListResponseDto.fromCategoryProductPageListResponseDto(productCategoryLists.hasNext(), productCategoryLists.isLast(), responses);
    }
}

