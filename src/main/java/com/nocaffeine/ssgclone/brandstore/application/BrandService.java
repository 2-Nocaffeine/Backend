package com.nocaffeine.ssgclone.brandstore.application;

import com.nocaffeine.ssgclone.brandstore.dto.BrandResponse;

public interface BrandService {

    BrandResponse findBrand(Long productId);
}
