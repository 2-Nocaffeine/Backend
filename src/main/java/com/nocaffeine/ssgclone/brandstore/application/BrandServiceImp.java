package com.nocaffeine.ssgclone.brandstore.application;

import com.nocaffeine.ssgclone.brandstore.domain.BrandList;
import com.nocaffeine.ssgclone.brandstore.dto.BrandResponse;
import com.nocaffeine.ssgclone.brandstore.infrastructure.BrandListRepository;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_DATA;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandServiceImp implements BrandService{

    private final BrandListRepository brandListRepository;
    @Override
    public BrandResponse findBrand(Long productId) {
        BrandList brandId = brandListRepository.findByProductId(productId)
                .orElseThrow(() -> new BaseException(NO_DATA));

        return BrandResponse.builder()
                .brandId(brandId.getBrand().getId())
                .brandName(brandId.getBrand().getName())
                .build();
    }
}
