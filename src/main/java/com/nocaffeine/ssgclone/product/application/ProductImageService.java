package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.product.dto.response.ProductImageResponseDto;

import java.util.List;

public interface ProductImageService {

    List<ProductImageResponseDto> getProductImageList(Long id);

}
