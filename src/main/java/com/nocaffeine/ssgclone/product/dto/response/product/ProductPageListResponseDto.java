package com.nocaffeine.ssgclone.product.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder // 빌더는 객체를 생성할 때 사용하는데, 생성자를 사용하지 않고 객체를 생성할 수 있다.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPageListResponseDto {

    private boolean next;
    private boolean last;
    private List<ProductResponseDto> productResponseDtoList;

    public static ProductPageListResponseDto fromProductPageListResponseDto(boolean next, boolean last, List<ProductResponseDto> productResponseDtoList) {
        return ProductPageListResponseDto.builder()
                .next(next)
                .last(last)
                .productResponseDtoList(productResponseDtoList)
                .build();
    }
}
