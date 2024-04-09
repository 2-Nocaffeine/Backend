package com.nocaffeine.ssgclone.product.vo.response.product;

import com.nocaffeine.ssgclone.product.dto.response.product.ProductResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ProductSearchResponseVo {

    private Long productId;

    public ProductSearchResponseVo(Long productId) {
        this.productId = productId;
    }

    public static List<ProductSearchResponseVo> productDtoToVo(List<ProductResponseDto> getProductResponseDto) {

        List<ProductSearchResponseVo> productSearchResponseVo = new ArrayList<>();

        for (ProductResponseDto productResponseDto : getProductResponseDto) {
            productSearchResponseVo.add(new ProductSearchResponseVo(
                    productResponseDto.getId()
            ));
        }

        return productSearchResponseVo;
    }
}
