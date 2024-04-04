package com.nocaffeine.ssgclone.product.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocaffeine.ssgclone.product.dto.response.OptionSelectedProductResponseDto;
import com.nocaffeine.ssgclone.product.dto.response.ProductOptionTypesResponseDto;
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
        );
    }
}
