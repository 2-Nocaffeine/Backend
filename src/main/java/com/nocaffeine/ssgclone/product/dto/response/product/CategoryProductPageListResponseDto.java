package com.nocaffeine.ssgclone.product.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryProductPageListResponseDto {

    private boolean next;
    private boolean last;
    private List<ProductIdListResponseDto> productIdListResponseDtoList;

    public static CategoryProductPageListResponseDto fromCategoryProductPageListResponseDto(
            boolean next, boolean last, List<ProductIdListResponseDto> productIdListResponseDtoList) {
        return CategoryProductPageListResponseDto.builder()
                .next(next)
                .last(last)
                .productIdListResponseDtoList(productIdListResponseDtoList)
                .build();
    }
}
