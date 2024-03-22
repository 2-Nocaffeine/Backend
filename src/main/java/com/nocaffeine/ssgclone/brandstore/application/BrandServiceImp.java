package com.nocaffeine.ssgclone.brandstore.application;

import com.nocaffeine.ssgclone.brandstore.domain.BrandList;
import com.nocaffeine.ssgclone.brandstore.dto.Response.BrandListResponse;
import com.nocaffeine.ssgclone.brandstore.infrastructure.BrandListRepository;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_BRAND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class BrandServiceImp implements BrandService {

    private final BrandListRepository brandListRepository;

    @Override
    public BrandListResponse findBrandName(Long productId) {
//        BrandList brandList = brandListRepository.findByProduct_Id(productId)
//                .orElseThrow(() -> new BaseException(NO_BRAND));
//
//        BrandListResponse brandListResponse = BrandListResponse.builder()
//                .product_id(productId)
//                .brand_id(brandList.getId())
//                .brand_name(brandList.getBrand().getName())
//                .build();
//        return brandListResponse;
        return null;
    }
}
//ProductList productList = productListRepository.findByProduct_Id(product_id)
//                .orElseThrow(() -> new BaseException(NO_PRODUCT));
//
//        ProductListResponse productListResponse = ProductListResponse.builder()
//                .productList_id(productList.getId())
//                .product_name(productList.getProduct().getName())
//                .product_price(productList.getProduct().getPrice())
//                .build();