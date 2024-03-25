package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.product.dto.AddOptionDto;
import com.nocaffeine.ssgclone.product.dto.ColorOptionDto;
import com.nocaffeine.ssgclone.product.dto.ProductDto;
import com.nocaffeine.ssgclone.product.dto.SizeOptionDto;

import java.util.List;

public interface ProductService {

     // 제품을 찾는 메소드
     ProductDto getProduct(Long id);
     List<SizeOptionDto> getSizeOptions(Long id);
     List<ColorOptionDto> getColorOptions(Long id);
     List<AddOptionDto> getAddOptions(Long id);

}
