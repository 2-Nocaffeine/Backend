package com.nocaffeine.ssgclone.brandstore.application;

import com.nocaffeine.ssgclone.brandstore.dto.Response.BrandListResponse;

import java.util.List;

public interface BrandService {
    BrandListResponse findBrandName(Long productId);
}
