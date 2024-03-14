package com.nocaffeine.ssgclone.product.service;

import com.nocaffeine.ssgclone.ResponseDto;
import com.nocaffeine.ssgclone.product.dto.ProductResponseDto;

public interface ProductService {

     ResponseDto<ProductResponseDto> getProduct(Long id);
}
