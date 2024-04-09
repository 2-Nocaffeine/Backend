package com.nocaffeine.ssgclone.product.vo.response.product;

import com.nocaffeine.ssgclone.product.dto.response.product.ProductResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ProductListResponseVo {

    private Long productId;

    public ProductListResponseVo(Long productId) {
        this.productId = productId;
    }


    public static List<ProductListResponseVo> productDtoToVo(List<ProductResponseDto> getAllProductResponseDto) {

        List<ProductListResponseVo> productListResponseVo = new ArrayList<>();

        for (ProductResponseDto productResponseDto : getAllProductResponseDto) {
            productListResponseVo.add(new ProductListResponseVo(
               productResponseDto.getId()
            ));
        }

        return productListResponseVo;
    }
}
