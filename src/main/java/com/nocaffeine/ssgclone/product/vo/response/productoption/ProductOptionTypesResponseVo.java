package com.nocaffeine.ssgclone.product.vo.response.productoption;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocaffeine.ssgclone.product.dto.response.productoption.ProductOptionTypesResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductOptionTypesResponseVo {

    @JsonProperty("Color")
    private boolean hasColorOption;
    @JsonProperty("Size")
    private boolean hasSizeOption;
    @JsonProperty("AddOption")
    private boolean hasAddOption;

    public ProductOptionTypesResponseVo(boolean hasColorOption, boolean hasSizeOption, boolean hasAddOption) {
        this.hasColorOption = hasColorOption;
        this.hasSizeOption = hasSizeOption;
        this.hasAddOption = hasAddOption;
    }


    public static ProductOptionTypesResponseVo productOptionTypesDtoToVo(ProductOptionTypesResponseDto productOptionTypesResponseDto) {
        return new ProductOptionTypesResponseVo(
                productOptionTypesResponseDto.getColorOptionId() != 1,
                productOptionTypesResponseDto.getSizeOptionId() != 1,
                productOptionTypesResponseDto.getAddOptionId() != 1
        ); // 옵션 타입이 없을 경우 1을 반환하므로 1이 아닐 경우에만 true 로 설정
    }
}
