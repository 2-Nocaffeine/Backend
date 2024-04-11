package com.nocaffeine.ssgclone.brandstore.application;

import com.nocaffeine.ssgclone.brandstore.domain.BrandList;
import com.nocaffeine.ssgclone.brandstore.dto.response.BrandProductIdPageListResponseDto;
import com.nocaffeine.ssgclone.brandstore.dto.response.BrandProductIdResponseDto;
import com.nocaffeine.ssgclone.brandstore.dto.response.BrandResponseDto;
import com.nocaffeine.ssgclone.brandstore.infrastructure.BrandListRepository;
import com.nocaffeine.ssgclone.brandstore.vo.response.BrandProductIdResponseVo;
import com.nocaffeine.ssgclone.common.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_DATA;
import static com.nocaffeine.ssgclone.common.exception.BaseResponseStatus.NO_EXIST_BRAND_PRODUCT;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandServiceImp implements BrandService {

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

        List<BrandProductIdResponseDto> responses = new ArrayList<>();

        for (BrandList brand : brandList) {
            responses.add(BrandProductIdResponseDto.fromBrandProductIdResponseDto(brand.getProduct()));
        }

        return responses;
    }

    @Override
    public BrandProductIdPageListResponseDto findBrandProductListPaged(Long brandId, Pageable page) {

        Page<BrandList> brandListPage = brandListRepository.findByBrandId(brandId, page);

        List<BrandProductIdResponseDto> responses = new ArrayList<>();

        for (BrandList brand : brandListPage) {
            responses.add(BrandProductIdResponseDto.fromBrandProductIdResponseDto(brand.getProduct()));
        }

        return BrandProductIdPageListResponseDto.fromBrandProductIdPageListResponseDto(brandListPage.hasNext(), brandListPage.isLast(), responses);
    }
}
