package com.nocaffeine.ssgclone.product.vo.response;

import com.nocaffeine.ssgclone.product.dto.ProductDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProductSearchResponseVo {

    private final Long productId;

    public ProductSearchResponseVo(Long productId) {
        this.productId = productId;
    }

    public static List<ProductSearchResponseVo> productDtoToVo(List<ProductDto> getProductDto) {

        List<ProductSearchResponseVo> productSearchResponseVo = new ArrayList<>();

        for (ProductDto productDto : getProductDto) {
            productSearchResponseVo.add(new ProductSearchResponseVo(
                    productDto.getId()
            ));
        }

        return productSearchResponseVo;
    }
}
