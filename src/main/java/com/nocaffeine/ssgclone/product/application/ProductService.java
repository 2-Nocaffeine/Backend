package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.product.dto.response.category.ProductCategoryResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.product.ProductPageListResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.product.ProductResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.productoption.AddOptionResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.productoption.ColorOptionResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.productoption.ProductOptionTypesResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.productoption.SizeOptionResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

     // 전체 상품 리스트를 가져오는 메소드
     List<ProductResponseDto> getAllProductId();

     // 제품 리스트를 10개씩 페이징을 해서 가져오는 메소드
     ProductPageListResponseDto getAllProducts(Pageable page);

     // 제품을 찾는 메소드
     ProductResponseDto getProduct(Long id);

     // 제품의 사이즈, 색상, 추가옵션을 가져오는 메소드
     List<SizeOptionResponseDto> getSizeOptions(Long id);

     List<ColorOptionResponseDto> getColorOptions(Long id);

     List<AddOptionResponseDto> getAddOptions(Long id);

     // 제품 검색 메소드
     List<ProductResponseDto> getSearchProducts(String keyword);

     ProductPageListResponseDto getSearchProductByKeyword(String searchKeyword, Pageable page);

     // 제품의 옵션 타입을 가져오는 메소드
     ProductOptionTypesResponseDto getOptionTypes(Long id);

     // 제품의 카테고리를 가져오는 메소드
     ProductCategoryResponseDto getCategory(Long id);
}
