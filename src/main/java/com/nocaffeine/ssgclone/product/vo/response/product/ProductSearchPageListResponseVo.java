package com.nocaffeine.ssgclone.product.vo.response.product;

import com.nocaffeine.ssgclone.product.dto.response.product.ProductPageListResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.product.ProductResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductSearchPageListResponseVo {

    private int nowPage;
    private int lastPage;
    private int totalPage;
    private boolean next;
    private boolean last;
    private List<ProductSearchResponseVo> productList;

    public ProductSearchPageListResponseVo(int nowPage, int totalPage, boolean next, boolean last, List<ProductResponseDto> productResponseDtoList) {
        this.nowPage = nowPage;
        this.lastPage = totalPage - 1;
        this.totalPage = totalPage;
        this.next = next;
        this.last = last;
        this.productList = ProductSearchResponseVo.productDtoToVo(productResponseDtoList);
    }

    public static ProductSearchPageListResponseVo fromProductPageListResponseDto(ProductPageListResponseDto productPageListResponseDto) {
        return new ProductSearchPageListResponseVo(
                productPageListResponseDto.getNowPage(),
                productPageListResponseDto.getTotalPage(),
                productPageListResponseDto.isNext(),
                productPageListResponseDto.isLast(),
                productPageListResponseDto.getProductResponseDtoList()
        );
    }
}
