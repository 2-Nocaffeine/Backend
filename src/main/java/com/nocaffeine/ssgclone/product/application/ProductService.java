package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.common.ResponseDto;
import com.nocaffeine.ssgclone.product.dto.ProductResponseDto;

public interface ProductService {

     ResponseDto<ProductResponseDto> getProduct(Long id);
}
