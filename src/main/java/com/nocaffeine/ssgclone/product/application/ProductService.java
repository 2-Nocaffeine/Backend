package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.product.dto.response.AddOptionResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.ColorOptionResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.ProductResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.SizeOptionResponseDto;

import java.util.List;

public interface ProductService {

     // 제품을 찾는 메소드
     ProductResponseDto getProduct(Long id);

     List<SizeOptionResponseDto> getSizeOptions(Long id);

     List<ColorOptionResponseDto> getColorOptions(Long id);

     List<AddOptionResponseDto> getAddOptions(Long id);

     List<ProductResponseDto> getSearchProducts(String keyword);
}
