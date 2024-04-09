package com.nocaffeine.ssgclone.brandstore.application;

import com.nocaffeine.ssgclone.brandstore.domain.BrandList;
import com.nocaffeine.ssgclone.brandstore.dto.response.BrandProductIdResponseDto;
import com.nocaffeine.ssgclone.brandstore.dto.response.BrandResponseDto;
import com.nocaffeine.ssgclone.brandstore.infrastructure.BrandListRepository;
import com.nocaffeine.ssgclone.brandstore.vo.response.BrandProductIdResponseVo;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_DATA;
import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_EXIST_BRAND_PRODUCT;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandServiceImp implements BrandService{

    private final BrandListRepository brandListRepository;
    @Override
    public BrandResponseDto findBrand(Long productId) {
        BrandList brandId = brandListRepository.findByProductId(productId)
                .orElseThrow(() -> new BaseException(NO_DATA));

        return BrandResponseDto.builder()
                .brandId(brandId.getBrand().getId())
                .brandName(brandId.getBrand().getName())
                .build();
    }

    @Override
    public List<BrandProductIdResponseDto> findBrandProductList(Long brandId) {
        List<BrandList> brandList = brandListRepository.findByBrandId(brandId);

        if (brandList.isEmpty()) {
            throw new BaseException(NO_EXIST_BRAND_PRODUCT);
        }

        List<BrandProductIdResponseDto> brandProductIdList = new ArrayList<>();

        for (BrandList brand : brandList) {
            BrandProductIdResponseDto brandProductId = BrandProductIdResponseDto.builder()
                    .productId(brand.getProduct().getId())
                    .build();
            brandProductIdList.add(brandProductId);
        }
        return brandProductIdList;
    }
}
