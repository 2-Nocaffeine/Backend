package com.nocaffeine.ssgclone.product.application;

import com.nocaffeine.ssgclone.common.CommonResponse;
import com.nocaffeine.ssgclone.product.domain.Product;
import com.nocaffeine.ssgclone.product.dto.ProductResponseDto;
import com.nocaffeine.ssgclone.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public CommonResponse<ProductResponseDto> getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){ // Optional 객체가 비어있는지 확인하는 메소드
            ProductResponseDto productResponseDto = convertToDto(product.get());
            return CommonResponse.success("제품을 찾았습니다.", productResponseDto);
        }
        return CommonResponse.fail("제품을 찾을 수 없습니다.", "ID가 " + id + "인 제품을 찾을 수 없습니다.");
    }

    private ProductResponseDto convertToDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .content(product.getContent())
                .discount(product.getDiscount())
                .build();
    }
}
