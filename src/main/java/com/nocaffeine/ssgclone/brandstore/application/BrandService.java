package com.nocaffeine.ssgclone.brandstore.application;

import com.nocaffeine.ssgclone.brandstore.dto.response.BrandProductIdResponseDto;
import com.nocaffeine.ssgclone.brandstore.dto.response.BrandResponseDto;

import java.util.List;

public interface BrandService {

    BrandResponseDto findBrand(Long productId);

    List<BrandProductIdResponseDto> findBrandProductList(Long brandId);
}
