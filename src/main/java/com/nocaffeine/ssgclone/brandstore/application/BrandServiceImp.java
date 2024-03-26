package com.nocaffeine.ssgclone.brandstore.application;

import com.nocaffeine.ssgclone.brandstore.domain.BrandList;
import com.nocaffeine.ssgclone.brandstore.dto.BrandResponse;
import com.nocaffeine.ssgclone.brandstore.infrastructure.BrandListRepository;
import com.nocaffeine.ssgclone.brandstore.infrastructure.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandServiceImp implements BrandService{

    private final BrandListRepository brandListRepository;
    @Override
    public BrandResponse findBrandId(Long productId) {
        BrandList brandID = brandListRepository.findByProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품의 브랜드가 존재하지 않습니다."));

        return BrandResponse.builder()
                .brandId(brandID.getBrand().getId())
                .brandName(brandID.getBrand().getName())
                .build();
    }
}
