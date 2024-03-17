package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.common.ResponseDto;
import com.nocaffeine.ssgclone.product.dto.request.AddOptionResponse;
import com.nocaffeine.ssgclone.product.dto.request.ColorOptionResponse;
import com.nocaffeine.ssgclone.product.dto.request.ProductResponse;
import com.nocaffeine.ssgclone.product.dto.request.SizeOptionResponse;

import java.util.List;

public interface ProductService {

     // 제품을 찾는 메소드
     ResponseDto<ProductResponse> getProduct(Long id);
     ResponseDto<List<SizeOptionResponse>> getSizeOptions(Long id);
     ResponseDto<List<ColorOptionResponse>> getColorOptions(Long id);
     ResponseDto<List<AddOptionResponse>> getAddOptions(Long id);

}
