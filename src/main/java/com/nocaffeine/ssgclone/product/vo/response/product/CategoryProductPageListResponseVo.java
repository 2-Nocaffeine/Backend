package com.nocaffeine.ssgclone.product.vo.response.product;

import com.nocaffeine.ssgclone.product.dto.response.product.CategoryProductPageListResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.product.ProductIdListResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryProductPageListResponseVo {

    private boolean next;
    private boolean last;
    private List<ProductIdListResponseVo> productIdList;

    public CategoryProductPageListResponseVo(boolean next, boolean last, List<ProductIdListResponseDto> productIdListResponseDtoList) {
        this.next = next;
        this.last = last;
        this.productIdList = ProductIdListResponseVo.convertToVo(productIdListResponseDtoList);
    }

    public static CategoryProductPageListResponseVo fromCategoryProductPageListResponseVo(CategoryProductPageListResponseDto categoryProductPageListResponseDto) {
        return new CategoryProductPageListResponseVo(
            categoryProductPageListResponseDto.isNext(),
            categoryProductPageListResponseDto.isLast(),
            categoryProductPageListResponseDto.getProductIdListResponseDtoList()
        );
    }
}
