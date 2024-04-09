package com.nocaffeine.ssgclone.product.vo.response.product;

import com.nocaffeine.ssgclone.product.dto.response.product.ProductPageListResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.product.ProductResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductPageListResponseVo {

    private boolean next;
    private boolean last;
    private List<ProductListResponseVo> productList;

    public ProductPageListResponseVo(boolean next, boolean last, List<ProductResponseDto> productList) {
        this.next = next;
        this.last = last;
        this.productList = ProductListResponseVo.productDtoToVo(productList);
    }

    public static ProductPageListResponseVo fromProductPageListResponseDto(ProductPageListResponseDto productPageListResponseDto) {
        return new ProductPageListResponseVo(
                productPageListResponseDto.isNext(),
                productPageListResponseDto.isLast(),
                productPageListResponseDto.getProductResponseDtoList()
        );
    }
}
