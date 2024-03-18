package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.product.dto.ProductResponseDto;

public interface ProductService {

     CommonResponse<ProductResponseDto> getProduct(Long id);
}
