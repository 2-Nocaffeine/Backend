package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.product.dto.response.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

     // 제품 리스트를 10개씩 페이징을 해서 가져오는 메소드
     ProductPageListResponseDto getAllProducts(Pageable page);

     // 제품을 찾는 메소드
     ProductResponseDto getProduct(Long id);

     List<SizeOptionResponseDto> getSizeOptions(Long id);

     List<ColorOptionResponseDto> getColorOptions(Long id);

     List<AddOptionResponseDto> getAddOptions(Long id);

     List<ProductResponseDto> getSearchProducts(String keyword);

     ProductOptionTypesResponseDto getOptionTypes(Long id);

     ProductCategoryResponseDto getCategory(Long id);
}
