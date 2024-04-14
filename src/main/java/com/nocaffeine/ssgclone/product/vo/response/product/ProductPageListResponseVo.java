package com.nocaffeine.ssgclone.product.vo.response.product;

import com.nocaffeine.ssgclone.product.dto.response.product.ProductPageListResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.product.ProductResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductPageListResponseVo {

    private int nowPage;
    private int lastPage;
    private int totalPage;
    private boolean next;
    private boolean last;
    private List<ProductListResponseVo> productList;

public ProductPageListResponseVo(int nowPage, int totalPage, boolean next, boolean last, List<ProductResponseDto> productResponseDtoList) {
        this.nowPage = nowPage;
        this.lastPage = totalPage - 1;
        this.totalPage = totalPage;
        this.next = next;
        this.last = last;
        this.productList = ProductListResponseVo.productDtoToVo(productResponseDtoList);
    }

    public static ProductPageListResponseVo fromProductPageListResponseDto(ProductPageListResponseDto productPageListResponseDto) {
        return new ProductPageListResponseVo(
                productPageListResponseDto.getNowPage(),
                productPageListResponseDto.getTotalPage(),
                productPageListResponseDto.isNext(),
                productPageListResponseDto.isLast(),
                productPageListResponseDto.getProductResponseDtoList()
        );
    }
}
