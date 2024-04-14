package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.product.dto.response.productimage.ProductImageResponseDto;

import java.util.List;

public interface ProductImageService {

    List<ProductImageResponseDto> getProductImageList(Long id);

}
